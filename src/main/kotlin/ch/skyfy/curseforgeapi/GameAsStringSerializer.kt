package ch.skyfy.curseforgeapi

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

class GameAsStringSerializer : KSerializer<Game> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("Game", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): Game {

        TODO("Not yet implemented")
    }

    override fun serialize(encoder: Encoder, value: Game) {
        TODO("Not yet implemented")
    }
}