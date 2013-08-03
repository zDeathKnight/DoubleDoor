package com.DoubleDoor.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class BlockDoorDouble extends BlockDoor
{
    public static final String woodName = "Wooden Double Door";
    public static final String woodInternalName = "woodDoubleDoor";
    public static final String ironName = "Iron Double Door";
    public static final String ironInternalName = "ironDoubleDoor";
    public static Block wood = null;
    public static Block iron = null;

    protected BlockDoorDouble(int var1, Material var2)
    {
        super(var1, var2);
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return (var1 & 8) != 0 ? 0 : (this.blockMaterial == Material.iron ? Item.doorIron.itemID : Item.doorWood.itemID);
    }

    @SideOnly(Side.CLIENT)

    /**
     * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
     */
    public int idPicked(World var1, int var2, int var3, int var4)
    {
        return this.blockMaterial == Material.iron ? Item.doorIron.itemID : Item.doorWood.itemID;
    }

    protected static int dX(int var0, boolean var1)
    {
        switch (var0 & 3)
        {
            case 1:
                return var1 ? 1 : -1;

            case 3:
                return var1 ? -1 : 1;

            default:
                return 0;
        }
    }

    protected static int dZ(int var0, boolean var1)
    {
        switch (var0 & 3)
        {
            case 0:
                return var1 ? -1 : 1;

            case 2:
                return var1 ? 1 : -1;

            default:
                return 0;
        }
    }

    protected void doubleDoorCheck(World var1, int var2, int var3, int var4, int var5)
    {
        boolean var6 = (16 & var5) != 0;
        int var7 = var2 + dX(var5, var6);
        int var8 = var4 + dZ(var5, var6);

        if (var1.getBlockId(var7, var3, var8) == this.blockID)
        {
            int var9 = this.getFullMetadata(var1, var7, var3, var8);

            if ((var9 ^ 20) == var5)
            {
                var1.setBlockMetadataWithNotify(var7, var3, var8, var5 & 7, 3);
                var1.markBlockRangeForRenderUpdate(var7, var3, var8, var7, var3 + 1, var8);
            }
        }
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9)
    {
        if (this.blockMaterial == Material.iron)
        {
            return false;
        }
        else
        {
            int var10 = 4 ^ this.getFullMetadata(var1, var2, var3, var4);
            int var11 = (var10 & 8) == 0 ? var3 : var3 - 1;
            var1.setBlockMetadataWithNotify(var2, var11, var4, var10 & 7, 3);
            var1.markBlockRangeForRenderUpdate(var2, var11, var4, var2, var11 + 1, var4);
            this.doubleDoorCheck(var1, var2, var11, var4, var10 & -9);
            var1.playAuxSFXAtEntity(var5, 1003, var2, var3, var4, 0);
            return true;
        }
    }

    /**
     * A function to open a door.
     */
    public void onPoweredBlockChange(World var1, int var2, int var3, int var4, boolean var5)
    {
        int var6 = 4 ^ this.getFullMetadata(var1, var2, var3, var4);
        boolean var7 = (var6 & 4) == 0;

        if (var7 != var5)
        {
            int var8 = (var6 & 8) == 0 ? var3 : var3 - 1;
            var1.setBlockMetadataWithNotify(var2, var8, var4, var6 & 7, 3);
            var1.markBlockRangeForRenderUpdate(var2, var8, var4, var2, var8 + 1, var4);
            this.doubleDoorCheck(var1, var2, var8, var4, var6 & -9);
            var1.playAuxSFXAtEntity((EntityPlayer)null, 1003, var2, var3, var4, 0);
        }
    }
}
