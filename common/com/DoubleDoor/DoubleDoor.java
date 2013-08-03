package com.DoubleDoor;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import com.DoubleDoor.lib.Reference;
import com.DoubleDoor.side.CommonProxy;
import com.DoubleDoor.blocks.BlockDoorDouble;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = Reference.MOD_ID, name=Reference.MOD_NAME, version=Reference.VERSION)
@NetworkMod(clientSideRequired=false,serverSideRequired=false)

public class DoubleDoor 
{
	
	@Instance
	public static DoubleDoor instance = new DoubleDoor();
	
	@SidedProxy(clientSide = Reference.clientSide, serverSide = Reference.serverSide)
	public static CommonProxy porxy;
	
	@PreInit
	public void PreLoad(FMLPreInitializationEvent event) 
    {
		
    }
	
	@Init
	public void load(FMLInitializationEvent event) 
    {
		Block.blocksList[71] = null;
		Block.blocksList[64] = null;
		
		Block.blocksList[71] = new BlockDoorDouble(71, Material.iron).setHardness(5.0F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("doorIron");
	    Block.blocksList[64] = new BlockDoorDouble(64, Material.wood).setHardness(3.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("doorWood");

    }
	
	@PostInit
	public static void postInit(FMLPostInitializationEvent event) 
	{
		
	}
	
}
