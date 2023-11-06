package lotto.domain;

public class WinningLotto {
    private final Lotto NUMBERS;
    private final int BONUS_NUMBER;

    public WinningLotto(Lotto lottoNumbers, int bonusNumber) {
        validateBonusNumberRange(bonusNumber);
        validateBonusNumberDuplication(lottoNumbers, bonusNumber);
        this.NUMBERS = lottoNumbers;
        this.BONUS_NUMBER = bonusNumber;
    }

    private void validateBonusNumberRange(int bonusNumber) {
        if(bonusNumber < 1 || bonusNumber > 45) {
            throw new IllegalArgumentException("[Error] 보너스 번호는 1부터 45까지 존재합니다.");
        }
    }

    private void validateBonusNumberDuplication(Lotto lottoNumbers, int bonusNumber) {
        if(lottoNumbers.getNumbers().contains(bonusNumber)) {
            throw new IllegalArgumentException("[Error] 로또 번호에 보너스 번호와 중복된 번호가 존재합니다.");
        }
    }
}
