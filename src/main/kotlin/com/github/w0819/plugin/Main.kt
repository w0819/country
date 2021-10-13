package com.github.w0819.plugin

import com.github.w0819.event.Listener
import io.github.monun.kommand.kommand
import net.kyori.adventure.text.Component.text
import net.projecttl.inventory.gui.gui
import net.projecttl.inventory.gui.utils.InventoryType
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin


class Main : JavaPlugin(){
    override fun onEnable() {
            server.pluginManager.registerEvents(Listener(this),this)
            kommand {
                register("country") {
                    then("Store") {
                        executes {
                            player.openOreStore(this@Main)
                        }
                    }
                }
                register("test") {
                    requires { playerOrNull != null && isOp }
                    executes {
                        player.openCountry(this@Main)
                    }
                }
            }
        val sm = server.scoreboardManager
        val sc = sm.mainScoreboard

        val soivet = sc.getTeam("Soivet")
        if (soivet == null) sc.registerNewTeam("Soivet")

        val us = sc.getTeam("us")
        if (us == null) sc.registerNewTeam("us")

        val sk = sc.getTeam("sk")
        if (sk == null) sc.registerNewTeam("sk")

        val nk = sc.getTeam("nk")
        if (nk == null) sc.registerNewTeam("nk")
            }
        }
fun Player.openOreStore(plugin: Main) {
    val ironMessage = "플레이어님 인벤토리 안에 철이 충분 하지 않아 구입에 실패 했습니다"

    this.gui(plugin, InventoryType.CHEST_9, text("${ChatColor.GRAY}Store")) {
        slot(0, ItemStack(Material.DIAMOND).apply {
            editMeta {
                it.displayName(text("${ChatColor.AQUA}다이야몬드 구매"))
                it.displayName(text("가격: 철15개"))
            }
        }) {
            if (player?.inventory?.contains(Material.IRON_INGOT,15) == true) {
                player?.inventory?.addItem(ItemStack(Material.DIAMOND,1))
                player?.inventory?.removeItem(ItemStack(Material.IRON_INGOT,15))
            } else {
                player?.sendMessage(ironMessage)
            }
        }
        slot(1,ItemStack(Material.IRON_INGOT).apply {
            editMeta {
                it.displayName(text("${ChatColor.GRAY}철 구매"))
                it.displayName(text("가격: 콘크리트 5개 or 빵 4개 or 철광석 1개"))
            }
        }) {
            if (player?.inventory?.contains(Material.RED_CONCRETE) == true) {
                player?.inventory?.removeItem(ItemStack(Material.RED_CONCRETE,5))
                player?.inventory?.addItem(ItemStack(Material.IRON_INGOT))
            } else if (player?.inventory?.contains(ItemStack(Material.BLUE_CONCRETE)) == true) {
                player?.inventory?.removeItem(ItemStack(Material.BLUE_CONCRETE,5))
                player?.inventory?.addItem(ItemStack(Material.IRON_INGOT))
            } else if (player?.inventory?.contains(Material.BREAD) == true) {
                player?.inventory?.removeItem(ItemStack(Material.BREAD,4))
                player?.inventory?.addItem(ItemStack(Material.IRON_INGOT))
            } else if (player?.inventory?.contains(ItemStack(Material.IRON_ORE,1)) == true) {
                player?.inventory?.removeItem(ItemStack(Material.IRON_ORE,1))
                player?.inventory?.addItem(ItemStack(Material.IRON_INGOT,1))
            }
        }
        slot(2, ItemStack(Material.GOLD_INGOT).apply {
            editMeta {
                it.displayName(text("${ChatColor.GOLD}금 구매"))
                it.displayName(text("가격: 콘트리트 4개 or 빵 2개 or 금광석 1개"))
            }
        }) {
            if (player?.inventory?.contains(Material.RED_CONCRETE) == true) {
                player?.inventory?.removeItem(ItemStack(Material.RED_CONCRETE,4))
                player?.inventory?.addItem(ItemStack(Material.GOLD_INGOT))
            } else if (player?.inventory?.contains(ItemStack(Material.BLUE_CONCRETE)) == true) {
                player?.inventory?.removeItem(ItemStack(Material.BLUE_CONCRETE,4))
                player?.inventory?.addItem(ItemStack(Material.GOLD_INGOT))
            } else if (player?.inventory?.contains(Material.BREAD) == true) {
                player?.inventory?.removeItem(ItemStack(Material.BREAD,2))
                player?.inventory?.addItem(ItemStack(Material.GOLD_INGOT))
            } else if (player?.inventory?.contains(ItemStack(Material.GOLD_ORE,1)) == true) {
                player?.inventory?.removeItem(ItemStack(Material.GOLD_ORE,1))
                player?.inventory?.addItem(ItemStack(Material.GOLD_INGOT,1))
            }
        }
    }
}
fun Player.openCountry(plugin: Main) {
    this.gui(plugin, InventoryType.CHEST_18, text("${ChatColor.GRAY}Country (결정전엔 창을 닫지 마시오)")){
        slot(0, ItemStack(Material.RED_CONCRETE).apply {
            editMeta {
                it.displayName(text("${ChatColor.RED}소련"))
                it.displayName(text("팀은 다시 바꿀수 없습니다 신중하계 결정해주십시오."))
                it.displayName(text("소련팀은 빨간 콘크리트 60개와 철 주괴 30개 그리고 낫과 곡갱이를 드립니다."))
            }
        }) {
            player?.inventory?.addItem(ItemStack(Material.RED_CONCRETE,60))
            player?.inventory?.addItem(ItemStack(Material.IRON_INGOT,30))
            player?.inventory?.addItem(ItemStack(Material.IRON_HOE))
            player?.inventory?.addItem(ItemStack(Material.IRON_PICKAXE))

            server.sendMessage(text("${player?.name}님의 팀은 소련입니다."))
            player?.sendMessage("당신의 팀은 소련팀으로 결정됐습니다.")
            close()
        }
        slot(1, ItemStack(Material.GRASS))
        slot(3, ItemStack(Material.GRASS))
        slot(5, ItemStack(Material.GRASS))
        slot(7, ItemStack(Material.GRASS))
        slot(9, ItemStack(Material.GRASS))
        slot(11, ItemStack(Material.GRASS))
        slot(13, ItemStack(Material.GRASS))
        slot(15, ItemStack(Material.GRASS))
        slot(17, ItemStack(Material.GRASS))
        slot(2, ItemStack(Material.LIGHT_BLUE_CONCRETE).apply {
            editMeta {
                it.displayName(text("${ChatColor.AQUA}미국"))
                it.displayName(text("팀은 다시 바꿀수 없습니다 신중하계 결정해주십시오."))
                it.displayName(text("철 도구 세트와 철 20개를 드립니다."))
            }
        }) {
            player?.inventory?.addItem(ItemStack(Material.BLUE_CONCRETE,64))
            player?.inventory?.addItem(ItemStack(Material.IRON_INGOT,25))
            player?.inventory?.addItem(ItemStack(Material.IRON_SWORD))
            player?.inventory?.addItem(ItemStack(Material.IRON_PICKAXE))
            player?.inventory?.addItem(ItemStack(Material.IRON_AXE))
            player?.inventory?.addItem(ItemStack(Material.IRON_SHOVEL))

            server.sendMessage(text("${player?.name}님의 팀은 미국입니다."))
            player?.sendMessage("당신의 팀은 미국팀으로 결정됐습니다.")
        }
        slot(4, ItemStack(Material.RED_CONCRETE).apply {
            editMeta {
                it.displayName(text("${ChatColor.RED}중국"))
                it.displayName(text("팀은 다시 바꿀수 없습니다 신중하게 결정해주세요"))
                it.displayName(text("빵 32개 철 20개 석탄 20개를 드립니다."))
            }
        }) {
            player?.inventory?.addItem(ItemStack(Material.RED_CONCRETE,60))
            player?.inventory?.addItem(ItemStack(Material.BREAD,32))
            player?.inventory?.addItem(ItemStack(Material.IRON_INGOT,20))
            player?.inventory?.addItem(ItemStack(Material.COAL,20))
            player?.inventory?.addItem(ItemStack(Material.IRON_HOE))
        }
        slot(6, ItemStack(Material.LIGHT_BLUE_CONCRETE).apply {
            editMeta {
                it.displayName(text("${ChatColor.AQUA}영국"))
                it.displayName(text("팀은 다시 바꿀수 없습니다 신중하게 결정해주요."))
                it.displayName(text("철 25개와 우유 1개 석탄 60개를 드립니다."))
            }
        }) {
            player?.inventory?.addItem(ItemStack(Material.BLUE_CONCRETE,64))
            player?.inventory?.addItem(ItemStack(Material.IRON_INGOT,25))
            player?.inventory?.addItem(ItemStack(Material.MILK_BUCKET))
            player?.inventory?.addItem(ItemStack(Material.COAL,64))
            server.sendMessage(text("${player?.name}님의 팀은 영국입니다."))
            player?.sendMessage(text("당신의 팀은 영국팀으로 결정됐습니다."))
        }
    }
}