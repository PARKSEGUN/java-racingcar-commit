package racingcar.service;


import static racingcar.constant.CarConstant.MAX_MOVE_CONDITION_NUMBER;
import static racingcar.constant.CarConstant.MIN_MOVE_CONDITION_NUMBER;
import static racingcar.constant.CarConstant.STANDARD_MOVE_CONDITION_NUMBER;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import racingcar.domain.Car;
import racingcar.domain.CarNames;
import racingcar.domain.GamePlayer;
import racingcar.domain.GameWinner;

/*
 *   racingCar게임의 기능들을 담당
 * */

public class GameService {

    public GamePlayer createGamePlayer(CarNames carNames) {
        List<Car> cars = carNames.createCars();
        return GamePlayer.from(cars);
    }

    public void moveCars(GamePlayer gamePlayer) {
        for (Car car : gamePlayer.getCars()) {
            moveCar(car);
        }
    }

    private void moveCar(Car car) {
        int randomNumber = Randoms.pickNumberInRange(MIN_MOVE_CONDITION_NUMBER, MAX_MOVE_CONDITION_NUMBER);
        if (randomNumber >= STANDARD_MOVE_CONDITION_NUMBER) {
            car.moveForward();
        }
    }

    public GameWinner createGameWinner(GamePlayer gamePlayer) {
        List<Car> cars = gamePlayer.getCars();
        int maxMoveDistance = gamePlayer.getMaxMoveDistance();
        List<Car> winningCars = cars.stream()
                .filter(car -> car.getMoveDistance() == maxMoveDistance)
                .toList();
        return GameWinner.from(winningCars);
    }
}
