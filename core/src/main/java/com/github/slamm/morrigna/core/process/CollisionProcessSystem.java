package com.github.slamm.morrigna.core.process;

import com.github.slamm.morrigna.core.map.PlayerRenderer;

public class CollisionProcessSystem {

    /**
     * current value of collision type
     * 0 is default map, 1 is house by river
     */
    public static int collisionType = 0;

    /**
     * all collision for main map stuff
     */
    private static void mapCollision() {
        // left wall
        if (PlayerRenderer.x < 110) {
            PlayerRenderer.isAbleToMoveLeft = false;
        }// segment 1
        else if (PlayerRenderer.y < 3554 && PlayerRenderer.y > 2466 && PlayerRenderer.x > 1374
                && PlayerRenderer.x < 1394) {
            PlayerRenderer.isAbleToMoveLeft = false;
        }// segment 3
        else if (PlayerRenderer.y < 3666 && PlayerRenderer.y > 3526 && PlayerRenderer.x > 1192
                && PlayerRenderer.x < 1250) {
            PlayerRenderer.isAbleToMoveLeft = false;
        }// segment 7
        else if (PlayerRenderer.y < 3706 && PlayerRenderer.y > 3606 && PlayerRenderer.x > 1363
                && PlayerRenderer.x < 1388) {
            PlayerRenderer.isAbleToMoveLeft = false;
        }// segment 10
        else if (PlayerRenderer.y < 3680 && PlayerRenderer.y > 3602 && PlayerRenderer.x > 1538
                && PlayerRenderer.x < 1568) {
            PlayerRenderer.isAbleToMoveLeft = false;
        }// segment 15
        else if (PlayerRenderer.y < 3550 && PlayerRenderer.y > 2240 && PlayerRenderer.x > 3002
                && PlayerRenderer.x < 3034) {
            PlayerRenderer.isAbleToMoveLeft = false;
        }// segment 17
        else if (PlayerRenderer.y < 2260 && PlayerRenderer.y > 2002 && PlayerRenderer.x > 2744
                && PlayerRenderer.x < 2782) {
            PlayerRenderer.isAbleToMoveLeft = false;
        }// segment 21
        else if (PlayerRenderer.y < 2260 && PlayerRenderer.y > 1946 && PlayerRenderer.x > 2672
                && PlayerRenderer.x < 2706) {
            PlayerRenderer.isAbleToMoveLeft = false;
        }// segment 23
        else if (PlayerRenderer.y < 2422 && PlayerRenderer.y > 2236 && PlayerRenderer.x > 1360
                && PlayerRenderer.x < 1394) {
            PlayerRenderer.isAbleToMoveLeft = false;
        }// segment 25
        else if (PlayerRenderer.y < 2422 && PlayerRenderer.y > 1878 && PlayerRenderer.x > 886 && PlayerRenderer.x < 944) {
            PlayerRenderer.isAbleToMoveLeft = false;
        }// segment 27
        else if (PlayerRenderer.y < 2034 && PlayerRenderer.y > 1900 && PlayerRenderer.x > 706 && PlayerRenderer.x < 718) {
            PlayerRenderer.isAbleToMoveLeft = false;
        }// segment 29
        else if (PlayerRenderer.y < 2422 && PlayerRenderer.y > 2030 && PlayerRenderer.x > 812 && PlayerRenderer.x < 852) {
            PlayerRenderer.isAbleToMoveLeft = false;
        }// segment 31
        else if (PlayerRenderer.y < 2532 && PlayerRenderer.y > 2396 && PlayerRenderer.x > 730 && PlayerRenderer.x < 770) {
            PlayerRenderer.isAbleToMoveLeft = false;
        }// segment 33
        else if (PlayerRenderer.y < 2516 && PlayerRenderer.y > 2466 && PlayerRenderer.x > 810 && PlayerRenderer.x < 848) {
            PlayerRenderer.isAbleToMoveLeft = false;
        }// segment 36
        else if (PlayerRenderer.y < 3442 && PlayerRenderer.y > 2916 && PlayerRenderer.x > 2894
                && PlayerRenderer.x < 2934) {
            PlayerRenderer.isAbleToMoveLeft = false;
        }// segment 38 HOUSE NEAR TOP RIGHT OF MAP
        else if (PlayerRenderer.y < 2930 && PlayerRenderer.y > 2856 && PlayerRenderer.x > 2856
                && PlayerRenderer.x < 2866) {
            PlayerRenderer.isAbleToMoveLeft = false;
        }// segment 40
        else if (PlayerRenderer.y < 2868 && PlayerRenderer.y > 2336 && PlayerRenderer.x > 2894
                && PlayerRenderer.x < 2934) {
            PlayerRenderer.isAbleToMoveLeft = false;
        }// segment 42
        else if (PlayerRenderer.y < 2820 && PlayerRenderer.y > 2338 && PlayerRenderer.x > 2068
                && PlayerRenderer.x < 2114) {
            PlayerRenderer.isAbleToMoveLeft = false;
        }// segment 46
        else if (PlayerRenderer.y < 2884 && PlayerRenderer.y > 2338 && PlayerRenderer.x > 2150
                && PlayerRenderer.x < 2190) {
            PlayerRenderer.isAbleToMoveLeft = false;
        }// segment 48
        else if (PlayerRenderer.y < 3108 && PlayerRenderer.y > 2866 && PlayerRenderer.x > 1464
                && PlayerRenderer.x < 1498) {
            PlayerRenderer.isAbleToMoveLeft = false;
        }// segment 52
        else if (PlayerRenderer.y < 3434 && PlayerRenderer.y > 3154 && PlayerRenderer.x > 1464
                && PlayerRenderer.x < 1500) {
            PlayerRenderer.isAbleToMoveLeft = false;
        }// segment 54
        else if (PlayerRenderer.y < 2258 && PlayerRenderer.y > 1192 && PlayerRenderer.x > 1466
                && PlayerRenderer.x < 1502) {
            PlayerRenderer.isAbleToMoveLeft = false;
        }// segment 56
        else if (PlayerRenderer.y < 1952 && PlayerRenderer.y > 1186 && PlayerRenderer.x > 2748
                && PlayerRenderer.x < 2780) {
            PlayerRenderer.isAbleToMoveLeft = false;
        } else {
            PlayerRenderer.isAbleToMoveLeft = true;
        }
        if (PlayerRenderer.x > 3788) {
            PlayerRenderer.isAbleToMoveRight = false;
        }// segment 1
        else if (PlayerRenderer.y < 3554 && PlayerRenderer.y > 2466 && PlayerRenderer.x > 1344
                && PlayerRenderer.x < 1383) {
            PlayerRenderer.isAbleToMoveRight = false;
        }// segment 3
        else if (PlayerRenderer.y < 3666 && PlayerRenderer.y > 3526 && PlayerRenderer.x > 1202
                && PlayerRenderer.x < 1240) {
            PlayerRenderer.isAbleToMoveRight = false;
        }// segment 5
        else if (PlayerRenderer.y < 3661 && PlayerRenderer.y > 3602 && PlayerRenderer.x > 1282
                && PlayerRenderer.x < 1302) {
            PlayerRenderer.isAbleToMoveRight = false;
        }// segment 8
        else if (PlayerRenderer.y < 3706 && PlayerRenderer.y > 3606 && PlayerRenderer.x > 1458
                && PlayerRenderer.x < 1488) {
            PlayerRenderer.isAbleToMoveRight = false;
        }// segment 12
        else if (PlayerRenderer.y < 3660 && PlayerRenderer.y > 3546 && PlayerRenderer.x > 1600
                && PlayerRenderer.x < 1610) {
            PlayerRenderer.isAbleToMoveRight = false;
        }// segment 13
        else if (PlayerRenderer.y < 3558 && PlayerRenderer.y > 3522 && PlayerRenderer.x > 1458
                && PlayerRenderer.x < 1488) {
            PlayerRenderer.isAbleToMoveRight = false;
        }// segment 15
        else if (PlayerRenderer.y < 3550 && PlayerRenderer.y > 2236 && PlayerRenderer.x > 2992
                && PlayerRenderer.x < 3024) {
            PlayerRenderer.isAbleToMoveRight = false;
        }// segment 17
        else if (PlayerRenderer.y < 2260 && PlayerRenderer.y > 2002 && PlayerRenderer.x > 2732
                && PlayerRenderer.x < 2766) {
            PlayerRenderer.isAbleToMoveRight = false;
        }// segment 19
        else if (PlayerRenderer.y < 2034 && PlayerRenderer.y > 1938 && PlayerRenderer.x > 3688
                && PlayerRenderer.x < 3708) {
            PlayerRenderer.isAbleToMoveRight = false;
        }// segment 21
        else if (PlayerRenderer.y < 2260 && PlayerRenderer.y > 1930 && PlayerRenderer.x > 2628
                && PlayerRenderer.x < 2696) {
            PlayerRenderer.isAbleToMoveRight = false;
        }// segment 23
        else if (PlayerRenderer.y < 2422 && PlayerRenderer.y > 2236 && PlayerRenderer.x > 1346
                && PlayerRenderer.x < 1384) {
            PlayerRenderer.isAbleToMoveRight = false;
        }// segment 25
        else if (PlayerRenderer.y < 2422 && PlayerRenderer.y > 1878 && PlayerRenderer.x > 876 && PlayerRenderer.x < 914) {
            PlayerRenderer.isAbleToMoveRight = false;
        }// segment 29
        else if (PlayerRenderer.y < 2422 && PlayerRenderer.y > 2030 && PlayerRenderer.x > 802 && PlayerRenderer.x < 842) {
            PlayerRenderer.isAbleToMoveRight = false;
        }// segment 31
        else if (PlayerRenderer.y < 2532 && PlayerRenderer.y > 2396 && PlayerRenderer.x > 718 && PlayerRenderer.x < 760) {
            PlayerRenderer.isAbleToMoveRight = false;
        }// segment 33
        else if (PlayerRenderer.y < 2516 && PlayerRenderer.y > 2466 && PlayerRenderer.x > 800 && PlayerRenderer.x < 828) {
            PlayerRenderer.isAbleToMoveRight = false;
        }// segment 36
        else if (PlayerRenderer.y < 3442 && PlayerRenderer.y > 2916 && PlayerRenderer.x > 2884
                && PlayerRenderer.x < 2924) {
            PlayerRenderer.isAbleToMoveRight = false;
        }// segment 40
        else if (PlayerRenderer.y < 2868 && PlayerRenderer.y > 2336 && PlayerRenderer.x > 2882
                && PlayerRenderer.x < 2924) {
            PlayerRenderer.isAbleToMoveRight = false;
        }// segment 44
        else if (PlayerRenderer.y < 2820 && PlayerRenderer.y > 2334 && PlayerRenderer.x > 1454
                && PlayerRenderer.x < 1498) {
            PlayerRenderer.isAbleToMoveRight = false;
        }// segment 46
        else if (PlayerRenderer.y < 2884 && PlayerRenderer.y > 2338 && PlayerRenderer.x > 2140
                && PlayerRenderer.x < 2180) {
            PlayerRenderer.isAbleToMoveRight = false;
        }// segment 48
        else if (PlayerRenderer.y < 3108 && PlayerRenderer.y > 2866 && PlayerRenderer.x > 1454
                && PlayerRenderer.x < 1488) {
            PlayerRenderer.isAbleToMoveRight = false;
        }// segment 50 ENTRANCE TO CENTRAL HOUSE
        else if (PlayerRenderer.y < 3178 && PlayerRenderer.y > 3090 && PlayerRenderer.x > 1486
                && PlayerRenderer.x < 1496) {
            PlayerRenderer.isAbleToMoveRight = false;
        }// segment 52
        else if (PlayerRenderer.y < 3434 && PlayerRenderer.y > 3154 && PlayerRenderer.x > 1454
                && PlayerRenderer.x < 1490) {
            PlayerRenderer.isAbleToMoveRight = false;
        }// segment 54
        else if (PlayerRenderer.y < 2258 && PlayerRenderer.y > 1192 && PlayerRenderer.x > 1456
                && PlayerRenderer.x < 1492) {
            PlayerRenderer.isAbleToMoveRight = false;
        }// segment 56
        else if (PlayerRenderer.y < 1952 && PlayerRenderer.y > 1186 && PlayerRenderer.x > 2738
                && PlayerRenderer.x < 2770) {
            PlayerRenderer.isAbleToMoveRight = false;
        } else {
            PlayerRenderer.isAbleToMoveRight = true;
        }
        // bottom wall
        if (PlayerRenderer.y < 224) {
            PlayerRenderer.isAbleToMoveDown = false;
        }
        // segment 2
        else if (PlayerRenderer.y > 3526 && PlayerRenderer.y < 3558 && PlayerRenderer.x > 1206
                && PlayerRenderer.x < 1390) {
            PlayerRenderer.isAbleToMoveDown = false;
        }// segment 14
        else if (PlayerRenderer.y > 3532 && PlayerRenderer.y < 3558 && PlayerRenderer.x > 1458
                && PlayerRenderer.x < 3022) {
            PlayerRenderer.isAbleToMoveDown = false;
        }// segment 16
        else if (PlayerRenderer.y > 2240 && PlayerRenderer.y < 2264 && PlayerRenderer.x > 2738
                && PlayerRenderer.x < 3028) {
            PlayerRenderer.isAbleToMoveDown = false;
        }// segment 18
        else if (PlayerRenderer.y > 2008 && PlayerRenderer.y < 2034 && PlayerRenderer.x > 2738
                && PlayerRenderer.x < 2836) {
            PlayerRenderer.isAbleToMoveDown = false;
        }// segment 20
        else if (PlayerRenderer.y > 1932 && PlayerRenderer.y < 1960 && PlayerRenderer.x > 2738
                && PlayerRenderer.x < 3836) {
            PlayerRenderer.isAbleToMoveDown = false;
        }// segment 22
        else if (PlayerRenderer.y > 2238 && PlayerRenderer.y < 2264 && PlayerRenderer.x > 1462
                && PlayerRenderer.x < 2690) {
            PlayerRenderer.isAbleToMoveDown = false;
        }// segment 24
        else if (PlayerRenderer.y > 2400 && PlayerRenderer.y < 2426 && PlayerRenderer.x > 880
                && PlayerRenderer.x < 1384) {
            PlayerRenderer.isAbleToMoveDown = false;
        }// segment 26
        else if (PlayerRenderer.y > 1888 && PlayerRenderer.y < 1910 && PlayerRenderer.x > 664
                && PlayerRenderer.x < 1502) {
            PlayerRenderer.isAbleToMoveDown = false;
        }// segment 28
        else if (PlayerRenderer.y > 2032 && PlayerRenderer.y < 2048 && PlayerRenderer.x > 664 && PlayerRenderer.x < 848) {
            PlayerRenderer.isAbleToMoveDown = false;
        }// segment 30
        else if (PlayerRenderer.y > 2400 && PlayerRenderer.y < 2426 && PlayerRenderer.x > 722 && PlayerRenderer.x < 848) {
            PlayerRenderer.isAbleToMoveDown = false;
        }// segment 34
        else if (PlayerRenderer.y > 2470 && PlayerRenderer.y < 2500 && PlayerRenderer.x > 806
                && PlayerRenderer.x < 1386) {
            PlayerRenderer.isAbleToMoveDown = false;
        }// segment 35
        else if (PlayerRenderer.y > 3418 && PlayerRenderer.y < 3448 && PlayerRenderer.x > 1458
                && PlayerRenderer.x < 2928) {
            PlayerRenderer.isAbleToMoveDown = false;
        }// segment 37
        else if (PlayerRenderer.y > 2920 && PlayerRenderer.y < 2946 && PlayerRenderer.x > 2822
                && PlayerRenderer.x < 2930) {
            PlayerRenderer.isAbleToMoveDown = false;
        }// segment 39
        else if (PlayerRenderer.y > 2848 && PlayerRenderer.y < 2872 && PlayerRenderer.x > 2822
                && PlayerRenderer.x < 2930) {
            PlayerRenderer.isAbleToMoveDown = false;
        }// segment 41
        else if (PlayerRenderer.y > 2340 && PlayerRenderer.y < 2372 && PlayerRenderer.x > 2144
                && PlayerRenderer.x < 2928) {
            PlayerRenderer.isAbleToMoveDown = false;
        }// segment 43
        else if (PlayerRenderer.y > 2790 && PlayerRenderer.y < 2824 && PlayerRenderer.x > 1458
                && PlayerRenderer.x < 2114) {
            PlayerRenderer.isAbleToMoveDown = false;
        }// segment 47
        else if (PlayerRenderer.y > 2872 && PlayerRenderer.y < 2900 && PlayerRenderer.x > 1458
                && PlayerRenderer.x < 2190) {
            PlayerRenderer.isAbleToMoveDown = false;
        }// segment 49
        else if (PlayerRenderer.y > 3090 && PlayerRenderer.y < 3112 && PlayerRenderer.x > 1458
                && PlayerRenderer.x < 1528) {
            PlayerRenderer.isAbleToMoveDown = false;
        } // segment 55
        else if (PlayerRenderer.y > 1198 && PlayerRenderer.y < 1222 && PlayerRenderer.x > 1460
                && PlayerRenderer.x < 2776) {
            PlayerRenderer.isAbleToMoveDown = false;
        } else {
            PlayerRenderer.isAbleToMoveDown = true;
        }
        // top wall
        if (PlayerRenderer.y > 3690) {
            PlayerRenderer.isAbleToMoveUp = false;
        }
        // segment 2
        else if (PlayerRenderer.y > 3522 && PlayerRenderer.y < 3548 && PlayerRenderer.x > 1206
                && PlayerRenderer.x < 1390) {
            PlayerRenderer.isAbleToMoveUp = false;
        }// segment 4
        else if (PlayerRenderer.y > 3654 && PlayerRenderer.y < 3664 && PlayerRenderer.x > 1245
                && PlayerRenderer.x < 1288) {
            PlayerRenderer.isAbleToMoveUp = false;
        }// segment 6
        else if (PlayerRenderer.y > 3602 && PlayerRenderer.y < 3616 && PlayerRenderer.x > 1286
                && PlayerRenderer.x < 1388) {
            PlayerRenderer.isAbleToMoveUp = false;
        }// segment 9
        else if (PlayerRenderer.y > 3602 && PlayerRenderer.y < 3636 && PlayerRenderer.x > 1464
                && PlayerRenderer.x < 1564) {
            PlayerRenderer.isAbleToMoveUp = false;
        }// segment 11
        else if (PlayerRenderer.y > 3654 && PlayerRenderer.y < 3666 && PlayerRenderer.x > 1562
                && PlayerRenderer.x < 1620) {
            PlayerRenderer.isAbleToMoveUp = false;
        }// segment 14
        else if (PlayerRenderer.y > 3522 && PlayerRenderer.y < 3548 && PlayerRenderer.x > 1458
                && PlayerRenderer.x < 3022) {
            PlayerRenderer.isAbleToMoveUp = false;
        }// segment 16
        else if (PlayerRenderer.y > 2230 && PlayerRenderer.y < 2254 && PlayerRenderer.x > 2738
                && PlayerRenderer.x < 3028) {
            PlayerRenderer.isAbleToMoveUp = false;
        }// segment 18
        else if (PlayerRenderer.y > 1998 && PlayerRenderer.y < 2024 && PlayerRenderer.x > 2738
                && PlayerRenderer.x < 3836) {
            PlayerRenderer.isAbleToMoveUp = false;
        }// segment 20
        else if (PlayerRenderer.y > 1922 && PlayerRenderer.y < 1950 && PlayerRenderer.x > 2738
                && PlayerRenderer.x < 3836) {
            PlayerRenderer.isAbleToMoveUp = false;
        }// segment 22
        else if (PlayerRenderer.y > 2228 && PlayerRenderer.y < 2254 && PlayerRenderer.x > 1462
                && PlayerRenderer.x < 2698) {
            PlayerRenderer.isAbleToMoveUp = false;
        }// segment 24
        else if (PlayerRenderer.y > 2390 && PlayerRenderer.y < 2416 && PlayerRenderer.x > 880
                && PlayerRenderer.x < 1384) {
            PlayerRenderer.isAbleToMoveUp = false;
        }// segment 26
        else if (PlayerRenderer.y > 1874 && PlayerRenderer.y < 1900 && PlayerRenderer.x > 664
                && PlayerRenderer.x < 1502) {
            PlayerRenderer.isAbleToMoveUp = false;
        }// segment 28
        else if (PlayerRenderer.y > 2022 && PlayerRenderer.y < 2058 && PlayerRenderer.x > 664 && PlayerRenderer.x < 848) {
            PlayerRenderer.isAbleToMoveUp = false;
        }// segment 30
        else if (PlayerRenderer.y > 2390 && PlayerRenderer.y < 2416 && PlayerRenderer.x > 722 && PlayerRenderer.x < 848) {
            PlayerRenderer.isAbleToMoveUp = false;
        }// segment 32 DOOR WAY NEAR RIVER HOUSE
        else if (PlayerRenderer.y > 2500 && PlayerRenderer.y < 2516 && PlayerRenderer.x > 758 && PlayerRenderer.x < 832) {
            PlayerRenderer.isAbleToMoveUp = false;
            HouseUpdateSystem.setInRiverHouse(true);
            HouseUpdateSystem.setJustEntered(true);
        }// segment 34
        else if (PlayerRenderer.y > 2460 && PlayerRenderer.y < 2486 && PlayerRenderer.x > 806
                && PlayerRenderer.x < 1386) {
            PlayerRenderer.isAbleToMoveUp = false;
        }// segment 35
        else if (PlayerRenderer.y > 3408 && PlayerRenderer.y < 3438 && PlayerRenderer.x > 1458
                && PlayerRenderer.x < 2928) {
            PlayerRenderer.isAbleToMoveUp = false;
        }// segment 37
        else if (PlayerRenderer.y > 2910 && PlayerRenderer.y < 2932 && PlayerRenderer.x > 2822
                && PlayerRenderer.x < 2930) {
            PlayerRenderer.isAbleToMoveUp = false;
        }// segment 39
        else if (PlayerRenderer.y > 2838 && PlayerRenderer.y < 2862 && PlayerRenderer.x > 2822
                && PlayerRenderer.x < 2930) {
            PlayerRenderer.isAbleToMoveUp = false;
        }// segment 41
        else if (PlayerRenderer.y > 2334 && PlayerRenderer.y < 2354 && PlayerRenderer.x > 2144
                && PlayerRenderer.x < 2930) {
            PlayerRenderer.isAbleToMoveUp = false;
        }// segment 45
        else if (PlayerRenderer.y > 2334 && PlayerRenderer.y < 2354 && PlayerRenderer.x > 1458
                && PlayerRenderer.x < 2110) {
            PlayerRenderer.isAbleToMoveUp = false;
        }// segment 47
        else if (PlayerRenderer.y > 2862 && PlayerRenderer.y < 2886 && PlayerRenderer.x > 1458
                && PlayerRenderer.x < 2190) {
            PlayerRenderer.isAbleToMoveUp = false;
        }// segment 51
        else if (PlayerRenderer.y > 3148 && PlayerRenderer.y < 3188 && PlayerRenderer.x > 1458
                && PlayerRenderer.x < 1528) {
            PlayerRenderer.isAbleToMoveUp = false;
        }
        // segment 53
        else if (PlayerRenderer.y > 2230 && PlayerRenderer.y < 2240 && PlayerRenderer.x > 1354
                && PlayerRenderer.x < 1386) {
            PlayerRenderer.isAbleToMoveUp = false;
        }
        // segment 55
        else if (PlayerRenderer.y > 1188 && PlayerRenderer.y < 1208 && PlayerRenderer.x > 1460
                && PlayerRenderer.x < 2776) {
            PlayerRenderer.isAbleToMoveUp = false;
        } // segment 55
        else if (PlayerRenderer.y > 1924 && PlayerRenderer.y < 1938 && PlayerRenderer.x > 2632
                && PlayerRenderer.x < 2698) {
            PlayerRenderer.isAbleToMoveUp = false;
        } else {
            PlayerRenderer.isAbleToMoveUp = true;
        }
    }

    /**
     * collision detection for in the river house
     */
    private static void riverHouseCollision() {
        /** Up stuff */
        // segment 1
        if (PlayerRenderer.y > 435) {
            PlayerRenderer.isAbleToMoveUp = false;
        } // segment 2
        else if (PlayerRenderer.y > 410 && PlayerRenderer.x < 336 && PlayerRenderer.x > 172) {
            PlayerRenderer.isAbleToMoveUp = false;
        }// segment 4
        else if (PlayerRenderer.y > 420 && PlayerRenderer.x < 400) {
            PlayerRenderer.isAbleToMoveUp = false;
        }// segment 7
        else if (PlayerRenderer.y > 414 && PlayerRenderer.x < 540 && PlayerRenderer.x > 482) {
            PlayerRenderer.isAbleToMoveUp = false;
        } // segment 11
        else if (PlayerRenderer.y > 330 && PlayerRenderer.x > 708) {
            PlayerRenderer.isAbleToMoveUp = false;
        } // segment 13
        else if (PlayerRenderer.y > 242 && PlayerRenderer.x > 722) {
            PlayerRenderer.isAbleToMoveUp = false;
        }// segment 20
        else if (PlayerRenderer.y > 308 && PlayerRenderer.y < 364 && PlayerRenderer.x < 234) {
            PlayerRenderer.isAbleToMoveUp = false;
        }// segment 26
        else if (PlayerRenderer.y > 260 && PlayerRenderer.y < 300 && PlayerRenderer.x > 390 && PlayerRenderer.x < 574) {
            PlayerRenderer.isAbleToMoveUp = false;
        } else {
            PlayerRenderer.isAbleToMoveUp = true;
        }
        /** Down Stuff */
        // segment 14
        if (PlayerRenderer.y < 160 && PlayerRenderer.x > 730) {
            PlayerRenderer.isAbleToMoveDown = false;
        } // segment 16 && 17 && 18
        else if (PlayerRenderer.y < 102) {
            PlayerRenderer.isAbleToMoveDown = false;
            if (PlayerRenderer.x > 460 && PlayerRenderer.x < 484) {
                HouseUpdateSystem.setInRiverHouse(false);
                HouseUpdateSystem.setJustExited(true);
            }
        } // segment 22
        else if (PlayerRenderer.y > 318 && PlayerRenderer.y < 374 && PlayerRenderer.x < 234) {
            PlayerRenderer.isAbleToMoveDown = false;
        }// segment 23
        else if (PlayerRenderer.y > 270 && PlayerRenderer.y < 322 && PlayerRenderer.x > 386 && PlayerRenderer.x < 574) {
            PlayerRenderer.isAbleToMoveDown = false;
        } else {
            PlayerRenderer.isAbleToMoveDown = true;
        }
        /** Left Stuff */
        // segment 3
        if (PlayerRenderer.y > 414 && PlayerRenderer.x < 336 && PlayerRenderer.x > 330) {
            PlayerRenderer.isAbleToMoveLeft = false;
        } // segment 5
        else if (PlayerRenderer.y > 424 && PlayerRenderer.x < 400) {
            PlayerRenderer.isAbleToMoveLeft = false;
        } // segment 8
        else if (PlayerRenderer.y > 418 && PlayerRenderer.x < 540 && PlayerRenderer.x > 520) {
            PlayerRenderer.isAbleToMoveLeft = false;
        }// segment 19
        else if (PlayerRenderer.x < 206) {
            PlayerRenderer.isAbleToMoveLeft = false;
        }// segment 21
        else if (PlayerRenderer.y > 314 && PlayerRenderer.y < 370 && PlayerRenderer.x < 238) {
            PlayerRenderer.isAbleToMoveLeft = false;
        }// segment 25
        else if (PlayerRenderer.y > 264 && PlayerRenderer.y < 318 && PlayerRenderer.x > 500 && PlayerRenderer.x < 578) {
            PlayerRenderer.isAbleToMoveLeft = false;
        } else {
            PlayerRenderer.isAbleToMoveLeft = true;
        }
        /** Right Stuff */
        // segment 6
        if (PlayerRenderer.y > 418 && PlayerRenderer.x > 482 && PlayerRenderer.x < 540) {
            PlayerRenderer.isAbleToMoveRight = false;
        } // segment 9
        else if (PlayerRenderer.x > 750) {
            PlayerRenderer.isAbleToMoveRight = false;
        }// segment 10
        else if (PlayerRenderer.y > 334 && PlayerRenderer.x > 704) {
            PlayerRenderer.isAbleToMoveRight = false;
        }// segment 12
        else if (PlayerRenderer.y > 246 && PlayerRenderer.x > 718) {
            PlayerRenderer.isAbleToMoveRight = false;
        } // segment 15
        else if (PlayerRenderer.y < 156 && PlayerRenderer.x > 726) {
            PlayerRenderer.isAbleToMoveRight = false;
        }// segment 24
        else if (PlayerRenderer.y < 318 && PlayerRenderer.y > 264 && PlayerRenderer.x > 382 && PlayerRenderer.x < 400) {
            PlayerRenderer.isAbleToMoveRight = false;
        } else {
            PlayerRenderer.isAbleToMoveRight = true;
        }
    }

    /**
     * current maps collision
     */
    public void update() {
        switch (collisionType) {
        case 0: {
            mapCollision();
            break;
        }
        case 1: {
            riverHouseCollision();
            break;
        }
        default:
            throw new RuntimeException("Should not get here");
        }
    }
}