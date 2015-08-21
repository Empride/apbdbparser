package app.model;

/**
 * Created by User on 19.08.2015.
 */
public class Item {
    private static final String APBDB_ITEM_URL = "http://apbdb.com";
    boolean isAlreadyParsed=false;

    String name;
    String url;
    double healthDamage;
    double vehicleDamage;
    double staminaDamage;

    double shotsToKill;
    double fireInterval;
    double timeToKill;
    String dropoffRange;
    String minDamageRange;
    String minDamagePercent;
    String maxRange;
    double reloadTime;
    double equipTime;
    String accuracyRadiusat10m;

    double perShotModifier;
    double shotModifierCap;
    double recoveryDelay;
    double recoveryPerSecond;
    double crouchModifier;
    double walkModifier;
    double runModifier;
    double sprintModifier;
    double jumpModifier;
    double marksmanModifier;
    double inVehicleModifier;
    double marksmanFoVWideScreen;
    double marksmanFoV4to3;
    String crouchedSpeed;
    String walkSpeed;
    String marksmanSpeed;
    String runSpeed;
    String sprintSpeed;
    double ammoCapacity;
    double magazineCapacity;


    public Item(String name, String url) {
        this.name = name;
        this.url = APBDB_ITEM_URL + url;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public void setProrerty(String stat, String value) {
    value=value.replaceAll(",",".");
        if (value.contains("(")) {
            value = value.substring(value.indexOf("(")+1,value.length()-1);
        }
        switch (stat) {
            case ("Time to Kill"): {
                timeToKill = Double.parseDouble(value.split("sec")[0]);
                break;
            }
            case ("Shots to Kill"): {
                shotsToKill = Double.parseDouble(value);
                break;
            }
            case ("Health Damage"): {
                healthDamage = Double.parseDouble(value);
                break;
            }
            case ("Hard Damage"): {
                vehicleDamage = Double.parseDouble(value);
                break;
            }
            case ("Dropoff Range"): {
                dropoffRange = value;
                break;
            }
            case ("Min Damage Range"): {
                minDamageRange =value;
                break;
            }
            case ("Max Range"): {
                maxRange = value;
                break;
            }
            case ("Minimum Damage %"): {
                minDamagePercent = value;
                break;
            }
            case ("Equip Time"): {
                equipTime = Double.parseDouble(value.split("sec")[0]);
                break;
            }
            case ("Reload Time"): {
                reloadTime = Double.parseDouble(value.split("sec")[0]);
                break;
            }
            case ("Accuracy Radius at 10m"): {
                accuracyRadiusat10m = value;
                break;
            }
            case ("Per Shot Modifier"): {
                perShotModifier = Double.parseDouble(value);
                break;
            }
            case ("Shot Modifier Cap"): {
                shotModifierCap = Double.parseDouble(value);
                break;
            }
            case ("Crouch Modifier"): {
                crouchModifier = Double.parseDouble(value);
                break;
            }
            case ("Walk Modifier"): {
                walkModifier = Double.parseDouble(value);
                break;
            }
            case ("Run Modifier"): {
                runModifier = Double.parseDouble(value);
                break;
            }
            case ("Sprint Modifier"): {
                sprintModifier = Double.parseDouble(value);
                break;
            }
            case ("Jump Modifier"): {
                jumpModifier = Double.parseDouble(value);
                break;
            }
            case ("Marksman Modifier"): {
                marksmanModifier = Double.parseDouble(value);
                break;
            }
            case ("In Vehicle Modifier"): {
                inVehicleModifier = Double.parseDouble(value);
                break;
            }
            case ("Marksman FoV 16:9"): {
                marksmanFoVWideScreen = Double.parseDouble(value);
                break;
            }
            case ("Marksman FoV 4:3"): {
                marksmanFoV4to3 = Double.parseDouble(value);
                break;
            }
            case ("Movement Speed"): {
                runSpeed = value;
                break;
            }
            case ("Crouched Speed"): {
                crouchedSpeed = value;
                break;
            }
            case ("Walk Speed"): {
                walkSpeed = value;
                break;
            }
            case ("Marksman Speed"): {
                marksmanSpeed = value;
                break;
            }
            case ("Run Speed"): {
                runSpeed = value;
                break;
            }
            case ("Sprint Speed"): {
                sprintSpeed = value;
                break;
            }
            case ("Ammo Capacity"): {
                ammoCapacity = Double.parseDouble(value);
                break;
            }
            case ("Magazine Capacity"): {
                magazineCapacity = Double.parseDouble(value);
                break;
            }
            case ("Stamina Damage"): {
                staminaDamage = Double.parseDouble(value);
                break;
            }
            case ("Fire Interval"): {
                fireInterval = Double.parseDouble(value.split("sec")[0]);;
                break;
            }
            case ("Recovery Delay"): {
                recoveryDelay = Double.parseDouble(value);
                break;
            }

            case ("Recovery Per Second"): {
                recoveryPerSecond = Double.parseDouble(value);
                break;
            }
            case ("Max Health Damage"): {
                healthDamage =  Double.parseDouble(value);
                break;
            }
            case ("Max Stamina Damage"): {
                staminaDamage =  Double.parseDouble(value);
                break;
            }
            case ("Max Hard Damage"): {
                vehicleDamage = Double.parseDouble(value);
                break;
            }


        }


    }

    public String getName() {
        return name;
    }

    public double getHealthDamage() {
        return healthDamage;
    }

    public double getVehicleDamage() {
        return vehicleDamage;
    }

    public double getStaminaDamage() {
        return staminaDamage;
    }

    public double getShotsToKill() {
        return shotsToKill;
    }

    public double getFireInterval() {
        return fireInterval;
    }

    public double getTimeToKill() {
        return timeToKill;
    }

    public String getDropoffRange() {
        return dropoffRange;
    }

    public String getMinDamageRange() {
        return minDamageRange;
    }

    public String getMinDamagePercent() {
        return minDamagePercent;
    }

    public String getMaxRange() {
        return maxRange;
    }

    public double getReloadTime() {
        return reloadTime;
    }

    public double getEquipTime() {
        return equipTime;
    }

    public String getAccuracyRadiusat10m() {
        return accuracyRadiusat10m;
    }



    public double getPerShotModifier() {
        return perShotModifier;
    }

    public double getShotModifierCap() {
        return shotModifierCap;
    }

    public double getRecoveryDelay() {
        return recoveryDelay;
    }

    public double getRecoveryPerSecond() {
        return recoveryPerSecond;
    }

    public double getCrouchModifier() {
        return crouchModifier;
    }

    public double getWalkModifier() {
        return walkModifier;
    }

    public double getRunModifier() {
        return runModifier;
    }

    public double getSprintModifier() {
        return sprintModifier;
    }

    public double getJumpModifier() {
        return jumpModifier;
    }

    public double getMarksmanModifier() {
        return marksmanModifier;
    }

    public double getInVehicleModifier() {
        return inVehicleModifier;
    }

    public double getMarksmanFoVWideScreen() {
        return marksmanFoVWideScreen;
    }

    public double getMarksmanFoV4to3() {
        return marksmanFoV4to3;
    }

    public String getCrouchedSpeed() {
        return crouchedSpeed;
    }

    public String getWalkSpeed() {
        return walkSpeed;
    }

    public String getMarksmanSpeed() {
        return marksmanSpeed;
    }

    public String getRunSpeed() {
        return runSpeed;
    }

    public String getSprintSpeed() {
        return sprintSpeed;
    }

    public double getAmmoCapacity() {
        return ammoCapacity;
    }

    public double getMagazineCapacity() {
        return magazineCapacity;
    }

    public boolean isAlreadyParsed() {
        return isAlreadyParsed;
    }

    public void setIsAlreadyParsed(boolean isAlreadyParsed) {
        this.isAlreadyParsed = isAlreadyParsed;
    }
}
