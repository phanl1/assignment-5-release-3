

public class startWar {

    Survivor[] survivors;
    Zombie[] zombies;

    startWar(Survivor[] survivors, Zombie[] zombies) {
        this.survivors = survivors;
        this.zombies = zombies;
    }

    void Start() {
        printInitialMessage(survivors, zombies);

        while (survivors.length > numDeadSurvivors(survivors) && zombies.length > numDeadZombies(zombies)) {
            attackSequence(survivors, zombies);
        }

        printFinalSurvivorCount(survivors);
    }

    static void printInitialMessage(Survivor[] survivors, Zombie[] zombies) {
        int numSurvivors = survivors.length;
        int numZombies = zombies.length;
        int numChildren = getChildCount(survivors);
        int numTeachers = getTeacherCount(survivors);
        int numSoldiers = getSoldierCount(survivors);
        int numCommonInfected = getCommonInfectedCount(zombies);
        int numTanks = getTankCount(zombies);
        String stringSurvivors = "We have " + numSurvivors + " survivors trying to make it to safety (";
        String stringZombies = "But there are " + numZombies + " zombies waiting for them (";
        String stringChildren = "Children";
        String stringTeachers = "Teachers";
        String stringSoldiers = "Soldiers";
        String stringTanks = "Tanks";

        // Change strings to singular form if only one object exists in that category
        if (numSurvivors == 1) {
            stringSurvivors = "We have " + numSurvivors + " survivor trying to make it to safety (";
        }
        if (numZombies == 1) {
            stringZombies = "But there are " + numZombies + " zombies waiting for them (";
        }
        if (numChildren == 1) {
            stringChildren = "Child";
        }
        if (numTeachers == 1) {
            stringTeachers = "Teacher";
        }
        if (numSoldiers == 1) {
            stringSoldiers = "Soldier";
        }
        if (numTanks == 1) {
            stringTanks = "Tank";
        }

        System.out.println(stringSurvivors
                + numChildren + " " + stringChildren + ", "
                + numTeachers + " " + stringTeachers + ", "
                + numSoldiers + " " + stringSoldiers + ")");
        System.out.println(stringZombies
                + numCommonInfected + " Common Infected, "
                + numTanks + " " + stringTanks + ")\n");
    }

    static void printFinalSurvivorCount(Survivor[] survivors) {
        // Print message stating if survivor(s) made it to safety
        if (survivors.length == numDeadSurvivors(survivors)) {
            if (survivors.length == 1) {
                System.out.println("\nThe survivor didn't make it.");
            } else {
                System.out.println("\nNone of the survivors made it.");
            }
        } // Some survivor(s) made it to safety
        else {
            if (survivors.length == 1) {
                System.out.println("\nIt seems they made it to safety.");
            } else {
                System.out.println("\nIt seems " + (survivors.length - numDeadSurvivors(survivors)) + " have made it to safety.");
            }
        }
    }

    static int getChildCount(Survivor[] survivors) {
        int numChildren = 0;

        for (Survivor survivor : survivors) {
            if (survivor instanceof Child) {
                numChildren++;
            }
        }

        return numChildren;
    }

    static int getTeacherCount(Survivor[] survivors) {
        int numTeachers = 0;

        for (Survivor survivor : survivors) {
            if (survivor instanceof Teacher) {
                numTeachers++;
            }
        }

        return numTeachers;
    }

    static int getSoldierCount(Survivor[] survivors) {
        int numSoldiers = 0;

        for (Survivor survivor : survivors) {
            if (survivor instanceof Soldier) {
                numSoldiers++;
            }
        }

        return numSoldiers;
    }

    static int getCommonInfectedCount(Zombie[] zombies) {
        int numCommonInfected = 0;

        for (Zombie zombie : zombies) {
            if (zombie instanceof CommonInfected) {
                numCommonInfected++;
            }

        }

        return numCommonInfected;
    }

    static int getTankCount(Zombie[] zombies) {
        int numTanks = 0;

        for (Zombie zombie : zombies) {
            if (zombie instanceof Tank) {
                numTanks++;
            }
        }

        return numTanks;
    }

    static void attackSequence(Survivor[] survivors, Zombie[] zombies) {
        for (Survivor survivor : survivors) {
            // Generate new random weapon
            Weapon weapon = new Weapon();

            if (!survivor.isDead) { // Check if survivor is alive, only attack if not dead
                for (Zombie zombie : zombies) {
                    if (!zombie.isDead) {
                        // Survivor attempts to attack each zombie with their random weapon
                        // We check to see if weapon accuracy is greater than random accuracy
                        // This is to generate random hit/miss result
                        if (weapon.checkAccuracy(weapon.getAccuracy())) {
                            survivor.attack(zombie, weapon.getDamage()); // Survivors attack zombies first
                            zombie.isDead(zombie.getHealth()); // Check if zombie is dead by checking if health is less than zero

                            if (zombie.isDead) {
                                System.out.println("\t" + survivor.getName()
                                        + " killed " + zombie.getName()
                                        + " with a " + weapon.getWeaponName() + ".");
                            }
                        } else {
                            System.out.println("\t" + survivor.getName()
                                    + " tried to attack " + zombie.getName()
                                    + " with a " + weapon.getWeaponName()
                                    + ", but they missed!");
                        }
                    }
                }
            }
        }

        for (Zombie zombie : zombies) {
            if (!zombie.isDead) { // Check if zombie is alive, only attack if not dead
                for (Survivor survivor : survivors) {
                    if (!survivor.isDead) {
                        zombie.attack(survivor, zombie.getAttack()); // Zombies attack survivors
                        survivor.isDead(survivor.getHealth()); // Check/set if survivor is dead by checking if health is less than zero

                        if (survivor.isDead) {
                            System.out.println("\t" + zombie.getName() + " killed " + survivor.getName() + ".");

                        }
                    }
                }
            }
        }
    }

    static int numDeadSurvivors(Survivor[] survivors) {
        int numDeadSurvivors = 0;

        for (Survivor survivor : survivors) {
            if (survivor.isDead) {
                numDeadSurvivors++;
            }
        }

        return numDeadSurvivors;
    }

    static int numDeadZombies(Zombie[] zombies) {
        int numDeadZombies = 0;

        for (Zombie zombie : zombies) {
            if (zombie.isDead) {
                numDeadZombies++;
            }
        }

        return numDeadZombies;
    }
}