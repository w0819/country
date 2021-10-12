package com.github.w0819.plugin

import event.Listener
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
                            player.openStore(this@Main)
                        }
                    }
                    then("Join") {
                            executes {
                                player.openCountry(this@Main)
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
fun Player.openStore(plugin: Main) {
    val ironMessage = "플레이어님 인벤토리 안에 철이 이충분 하지 않아 구입에 실패 했습니다"

    this.gui(plugin, InventoryType.CHEST_9, text("${ChatColor.GRAY}Store")) {
        slot(0, ItemStack(Material.DIAMOND).apply {
            editMeta {
                it.displayName(text("${ChatColor.AQUA}다이야몬드 구매"))
            }
        }) {
            if (player?.inventory?.contains(Material.IRON_INGOT,10) == true) {
                player?.inventory?.addItem(ItemStack(Material.DIAMOND,1))
                player?.inventory?.removeItem(ItemStack(Material.IRON_INGOT,10))
            } else {
                player?.sendMessage(ironMessage)
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
                it.displayName(text("소련팀은 빨간 콘크리트 64개와 철 주괴 25개를 드립니다."))
            }
        }) {
            player?.inventory?.addItem(ItemStack(Material.RED_CONCRETE,128))
            player?.inventory?.addItem(ItemStack(Material.IRON_INGOT,25))

            server.sendMessage(text("${player?.name}님의 팀은 소련입니다."))
            player?.sendMessage("당신의 팀은 소련팀으로 결정됐습니다.")
            close()
        }
        slot(2, ItemStack(Material.BLUE_CONCRETE).apply {
            editMeta {
                it.displayName(text("${ChatColor.AQUA}미국"))
                it.displayName(text("팀은 다시 바꿀수 없습니다 신중하계 결정해주십시오."))
                it.displayName(text("철 도구 세트와 파란색 콘크리트 64개 그리고 철 15개를 드립니다."))
            }
        }) {
            player?.inventory?.addItem(ItemStack(Material.BLUE_CONCRETE,64))
            player?.inventory?.addItem(ItemStack(Material.IRON_INGOT,15))
            player?.inventory?.addItem(ItemStack(Material.IRON_SWORD))
            player?.inventory?.addItem(ItemStack(Material.IRON_PICKAXE))
            player?.inventory?.addItem(ItemStack(Material.IRON_AXE))
            player?.inventory?.addItem(ItemStack(Material.IRON_SHOVEL))

            server.sendMessage(text("${player?.name}님의 팀은 미국입니다."))
            player?.sendMessage("당신의 팀은 미국팀으로 결정됐습니다.")
        }
    }
}