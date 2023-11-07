package lotto.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("로또 결과에 대한 테스트")
class LottoResultTest {
    static final WinningLotto WINNING_LOTTO = new WinningLotto(new Lotto(List.of(8, 2, 3, 4, 6, 5)), 7);
    @Test
    void 당첨_결과_초기상태() {
        LottoResult lottoResult = new LottoResult(1000);
        for (LottoPrize prize : LottoPrize.values()) {
            assertEquals(0, lottoResult.prizeResult.get(prize));
        }
    }

    @Test
    void 당첨_결과_1등_2등_3등() {
        LottoResult lottoResult = new LottoResult(1000);
        List<Lotto> lottos = Arrays.asList(
                new Lotto(List.of(6, 2, 8, 3, 4, 5)),  
                new Lotto(List.of(7, 2, 3, 4, 5, 6)),
                new Lotto(List.of(9, 2, 3, 4, 5, 6))
        );

        lottoResult.calculateWinningResult(WINNING_LOTTO, lottos);

        assertEquals(1, lottoResult.getPrizeResult().get(LottoPrize.FIRST));
        assertEquals(1, lottoResult.getPrizeResult().get(LottoPrize.SECOND));
        assertEquals(1, lottoResult.getPrizeResult().get(LottoPrize.THIRD));
        assertEquals(0, lottoResult.getPrizeResult().get(LottoPrize.FOURTH));
        assertEquals(0, lottoResult.getPrizeResult().get(LottoPrize.FIFTH));
        assertEquals(0, lottoResult.getPrizeResult().get(LottoPrize.ETC));
    }

    @Test
    void 당첨_결과_4등_5등_꽝_보너스번호_포함() {
        LottoResult lottoResult = new LottoResult(1000);
        List<Lotto> lottos = Arrays.asList(
                new Lotto(List.of(7, 2, 3, 4, 6, 45)),
                new Lotto(List.of(11, 2, 10, 3, 4, 12)),
                new Lotto(List.of(7, 9, 11, 13, 15, 17))
        );

        lottoResult.calculateWinningResult(WINNING_LOTTO, lottos);

        assertEquals(0, lottoResult.getPrizeResult().get(LottoPrize.FIRST));
        assertEquals(0, lottoResult.getPrizeResult().get(LottoPrize.SECOND));
        assertEquals(0, lottoResult.getPrizeResult().get(LottoPrize.THIRD));
        assertEquals(1, lottoResult.getPrizeResult().get(LottoPrize.FOURTH));
        assertEquals(1, lottoResult.getPrizeResult().get(LottoPrize.FIFTH));
        assertEquals(1, lottoResult.getPrizeResult().get(LottoPrize.ETC));
    }

    @Test
    void 당첨_결과_같은등수_여러장() {
        LottoResult lottoResult = new LottoResult(1000);
        List<Lotto> lottos = Arrays.asList(
                new Lotto(List.of(9, 2, 3, 4, 5, 6)),
                new Lotto(List.of(9, 2, 3, 4, 5, 6))
        );

        lottoResult.calculateWinningResult(WINNING_LOTTO, lottos);

        assertEquals(0, lottoResult.getPrizeResult().get(LottoPrize.FIRST));
        assertEquals(0, lottoResult.getPrizeResult().get(LottoPrize.SECOND));
        assertEquals(2, lottoResult.getPrizeResult().get(LottoPrize.THIRD));
        assertEquals(0, lottoResult.getPrizeResult().get(LottoPrize.FOURTH));
        assertEquals(0, lottoResult.getPrizeResult().get(LottoPrize.FIFTH));
        assertEquals(0, lottoResult.getPrizeResult().get(LottoPrize.ETC));
    }
}