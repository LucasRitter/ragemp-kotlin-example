package design.lucasritter.example

// Make sure to add this line whenever you are working with RageMP stuff.
import mp.rage.*
import kotlin.js.Date

public class Example {
    constructor() {
        mp.gui.chat.push("An instance of Example has been created!")
        // Let's add an handler to the 'render' event.
        mp.events.add("render", { this.handleRenderEvent() })

        // We should also create a car so we can test the speedometer.
        mp.vehicles.new(mp.game.joaat("t20"), mp.players.local.position, MpVehicleOptions(numberPlate = "GitHub"))

        // And here we push an update to the Discord client.
        mp.discord.update("Example Server", "Driving around")
    }

    private fun handleRenderEvent() {
        // Let's draw a simple speedometer for when the player sits in a car.
        if (mp.players.local.isInAnyVehicle(false) && mp.players.local.vehicle != null) {
            // Here we draw a rectangle on the screen that scales to the current RPM.
            mp.game.graphics.drawRect(0,0.995, mp.players.local.vehicle?.rpm as Float * 0.5, 0.01, 255, 52, 40, 127)
        }

    }
}