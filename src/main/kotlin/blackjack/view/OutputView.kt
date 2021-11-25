package blackjack.view

import blackjack.domain.gamer.Dealer
import blackjack.domain.gamer.Gamer
import blackjack.domain.gamer.Player
import blackjack.domain.result.DealerResult

class OutputView {

    companion object {
        private const val PRINT_START_GAME = "에게 2장의 카드를 나누었습니다.\n"
        private const val BLACKJACK_END_NUMBER = 21

        fun printStartGame(gamers: List<Gamer>) {
            val playerNames = gamers.joinToString { it.name }
            println("${playerNames}$PRINT_START_GAME")

            for (gamer in gamers) {
                if (gamer is Dealer) {
                    println("${gamer.name} 카드: ${gamer.cards.value[0]}")
                } else {
                    printGamerCard(gamer)
                }
            }
        }

        fun printGamerCard(gamer: Gamer) {
            println("${gamer.name} 카드: ${gamer.haveCards()}")
        }

        fun printBlackjackResult(gamers: List<Gamer>) {
            println("\n---블랙잭 결과---")
            for (gamer in gamers) {
                val blackjackResult = getBlackjackResult(gamer)
                println("${gamer.name}카드: ${gamer.haveCards()} - 결과: $blackjackResult")
            }
        }

        private fun getBlackjackResult(gamer: Gamer): String {
            val totalScore = gamer.cards.getTotalScore()
            return if (totalScore >= BLACKJACK_END_NUMBER) {
                gamer.state.toString()
            } else {
                totalScore.toString()
            }
        }

        fun printCurrentDealerScore(currentScore: Int) {
            return if (currentScore > 16) {
                println("딜러는 17이상이라 카드를 더 받을 수 없습니다.")
            } else {
                println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
            }
        }

        fun printFinalOutcome(dealerResult: DealerResult, players: List<Player>) {
            println("\n## 최종 승패")
            println("딜러: ${getDealerResult(dealerResult)}")
            for (player in players) {
                println("${player.name}: ${player.result()}")
            }
        }

        private fun getDealerResult(dealerResult: DealerResult): String {
            return (if (dealerResult.win > 0) "${dealerResult.win}승 " else "")
                .plus(if (dealerResult.push > 0) "${dealerResult.push}무 " else "")
                .plus(if (dealerResult.lose > 0) "${dealerResult.lose}패" else "")
        }
    }
}