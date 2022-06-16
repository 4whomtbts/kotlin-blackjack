package blackjack.view.output.converter

import blackjack.domain.CardNumber
import blackjack.domain.Player
import blackjack.domain.PlayerName
import blackjack.domain.PlayingCard
import blackjack.domain.PlayingCards
import blackjack.domain.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerConverterTest {
    @Test
    fun `PlayerConverter는 Player를 출력을 위한 문자열로 변환해 반환한다`() {
        val player = Player(
            name = PlayerName("joker"),
            initialCards = PlayingCards.from(
                listOf(
                    PlayingCard(Suit.SPADES, CardNumber.TWO),
                    PlayingCard(Suit.HEARTS, CardNumber.ACE)
                )
            )
        )

        val expected = "joker카드: 2스페이드, A하트"
        assertThat(PlayerConverter.convert(player)).isEqualTo(expected)
    }
}