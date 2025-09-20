package com.adccadc.rust.manager;

import com.adccadc.rust.Rust;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;

/**
 * 锈蚀管理器
 * @author CampioneBase
 * @version 1.0
 */
public class RustManager {
    public static final String[] RUST_TEXT = {"", "斑驳的", "锈蚀的", "氧化的"};
    public static final String WAXED_TEXT = "涂蜡的";
    // 锈蚀状态
    private final RustState state = new RustState();
    // 锈蚀最大等级
    private final int maxLevel;
    // 锈蚀物品类型
    private final ItemStack rust;

    /**
     * @param rust 附着的锈蚀物品堆
     * @param max 锈蚀最大等级
     */
    public RustManager(ItemStack rust, int max){
        this.maxLevel = max;
        this.rust = rust;
    }

    public void setState(RustState state){
        this.state.level = state.level;
        this.state.isWaxed = state.isWaxed;
    }

    /**
     * 尝试上锈
     * @return 是否成功上锈
     */
    public boolean tryRusted(){
        if (this.state.isWaxed) return false;
        if (this.state.level >= maxLevel) return false;
        this.state.level++;
        return true;
    }

    /**
     * 尝试去锈
     * @return 去锈时的掉落物，null 表示失败
     */
    @Nullable
    public ItemStack tryDerusted(){
        // 脱蜡
        if (this.state.isWaxed) {
            this.state.isWaxed = false;
            return ItemStack.EMPTY;
        }
        if (this.state.level <= 0) return null;
        this.state.level--;
        return rust.copy();
    }

    /**
     * 上蜡
     * @return 是否成功上蜡
     */
    public boolean tryWaxed(){
        // 是否已经上蜡
        if (this.state.isWaxed) return false;
        // 成功上蜡
        this.state.isWaxed = true;
        return true;
    }

    public RustState getState(){
        return state;
    }

    public void setLevel(int level) {
        this.state.level = level;
    }

    public void setWaxed(boolean isWaxed){
        this.state.isWaxed = isWaxed;
    }

    public String getName(){
        return (this.state.isWaxed ? WAXED_TEXT : "") + RUST_TEXT[this.state.level];
    }
}
