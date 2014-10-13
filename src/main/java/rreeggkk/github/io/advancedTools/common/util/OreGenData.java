package rreeggkk.github.io.advancedTools.common.util;

import net.minecraft.block.Block;

public class OreGenData {
	
	private int maxHeight = 0;
	private int minHeight = 0;
	private int orePerVein = 0;
	private int veinPerChunk = 0;
	private Block block;
	
	public OreGenData(){}
	public int getMaxHeight() {
		return maxHeight;
	}
	public void setMaxHeight(int maxHeight) {
		this.maxHeight = maxHeight;
	}
	public int getMinHeight() {
		return minHeight;
	}
	public void setMinHeight(int minHeight) {
		this.minHeight = minHeight;
	}
	public int getOrePerVein() {
		return orePerVein;
	}
	public void setOrePerVein(int orePerVein) {
		this.orePerVein = orePerVein;
	}
	public int getVeinPerChunk() {
		return veinPerChunk;
	}
	public void setVeinPerChunk(int veinPerChunk) {
		this.veinPerChunk = veinPerChunk;
	}
	public Block getBlock() {
		return block;
	}
	public void setBlock(Block block) {
		this.block = block;
	}
}