package telran.range;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class BrokenFloorTest {

    private int getMinimalBrokenFloor(BallBrokenFloor bbf) {
        int bottomFloor = 0;
        int topFloor = bbf.getNFloors();
        int res = -1;

        while (bottomFloor <= topFloor) {
            int mid = bottomFloor + (topFloor - bottomFloor) / 2;
            try {
                bbf.checkFloor(mid);
                bottomFloor = mid + 1;
            } catch (Exception e) {
                res = mid;
                topFloor = mid - 1;
            }
        }
        return res;
    }

    @Test
    void minimalBrokenFloorRTest() {
        int[] floors = { 200, 17, 1001, 2000 };
        for (int i = 0; i < floors.length; i++) {
            BallBrokenFloor bbf = new BallBrokenFloor(floors[i]);
            assertEquals(bbf.getMinBrokenFloor(), getMinimalBrokenFloor(bbf));
        }
    }
}