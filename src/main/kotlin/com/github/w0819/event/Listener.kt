package event

import com.github.w0819.plugin.Main
import com.github.w0819.plugin.openCountry
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.Component.text
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerLoginEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.inventory.ItemStack

class Listener(private val plugin: Main) : Listener{
    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        event.joinMessage(text("${event.player.name}님이 접속하셨습니다."))
    }
    @EventHandler
    fun onPlayerQuit(event: PlayerQuitEvent) {
        event.quitMessage(text("${event.player.name}님이 퇴장하셨습니다."))
    }
    @EventHandler
    fun onPlayerFirstJoin(event: PlayerLoginEvent) {
        val player = event.player
        if (player.hasPlayedBefore()) {
            player.inventory.setItem(1, ItemStack(Material.STONE_SWORD))
            player.inventory.setItem(2, ItemStack(Material.STONE_AXE))
            player.inventory.setItem(3, ItemStack(Material.STONE_PICKAXE))
        }
        event.player.openCountry(plugin)
    }
}