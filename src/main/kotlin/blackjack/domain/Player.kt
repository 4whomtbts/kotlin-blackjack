package blackjack.domain

class Player(
    val name: PlayerName,
    initialCards: PlayingCards
) {
    private var hands = Hands.from(initialCards)
    val cardsOfHands: PlayingCards
        get() = hands.cards
    val score: Score
        get() = hands.score

    fun receive(playingCards: PlayingCards) {
        hands += playingCards
    }

    fun stay() {
        hands = hands.stay()
    }

    fun isReceivable(): Boolean = hands.isReceivable()
}