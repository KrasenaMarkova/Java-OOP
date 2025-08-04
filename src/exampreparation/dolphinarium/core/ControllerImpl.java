package exampreparation.dolphinarium.core;

import exampreparation.dolphinarium.entities.dolphins.BottleNoseDolphin;
import exampreparation.dolphinarium.entities.dolphins.Dolphin;
import exampreparation.dolphinarium.entities.dolphins.SpinnerDolphin;
import exampreparation.dolphinarium.entities.dolphins.SpottedDolphin;
import exampreparation.dolphinarium.entities.foods.Food;
import exampreparation.dolphinarium.entities.foods.Herring;
import exampreparation.dolphinarium.entities.foods.Mackerel;
import exampreparation.dolphinarium.entities.foods.Squid;
import exampreparation.dolphinarium.entities.pools.DeepWaterPool;
import exampreparation.dolphinarium.entities.pools.Pool;
import exampreparation.dolphinarium.entities.pools.ShallowWaterPool;
import exampreparation.dolphinarium.repositories.FoodRepositoryImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.stream.Collectors;

import static exampreparation.dolphinarium.common.ConstantMessages.*;
import static exampreparation.dolphinarium.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {

    /*public class ControllerImpl implements Controller {
        private FoodRepository foodRepository;
        private Map<String, Pool> pools;

        public ControllerImpl() {
            this.foodRepository = new FoodRepositoryImpl();
            this.pools = new LinkedHashMap<>();
        }

        @Override
        public String addPool(String poolType, String poolName) {
            Pool pool = switch (poolType) {
                case "DeepWaterPool" -> new DeepWaterPool(poolName);
                case "ShallowWaterPool" -> new ShallowWaterPool(poolName);
                default -> throw new NullPointerException(INVALID_POOL_TYPE);
            };

            if (pools.containsKey(pool.getName())) {
                throw new NullPointerException(POOL_EXISTS);
            }

            pools.put(pool.getName(), pool);

            return String.format(SUCCESSFULLY_ADDED_POOL_TYPE, poolType, poolName);
        }

        @Override
        public String buyFood(String foodType) {
            Food food = switch (foodType) {
                case "Herring" -> new Herring();
                case "Mackerel" -> new Mackerel();
                case "Squid" -> new Squid();
                default -> throw new IllegalArgumentException(INVALID_FOOD_TYPE);
            };
            foodRepository.add(food);
            return String.format(SUCCESSFULLY_BOUGHT_FOOD_TYPE, foodType);
        }

        @Override
        public String addFoodToPool(String poolName, String foodType) {
            Food food = foodRepository.findByType(foodType);
            if (food == null) {
                throw new IllegalArgumentException(String.format(NO_FOOD_FOUND, foodType));
            }

            Pool pool = pools.get(poolName);
            pool.addFood(food);
            return String.format(SUCCESSFULLY_ADDED_FOOD_IN_POOL, foodType, poolName);
        }

        @Override
        public String addDolphin(String poolName, String dolphinType, String dolphinName, int energy) {
            Dolphin dolphin = switch (dolphinType) {
                case "BottleNoseDolphin" -> new BottleNoseDolphin(dolphinName, energy);
                case "SpottedDolphin" -> new SpottedDolphin(dolphinName, energy);
                case "SpinnerDolphin" -> new SpinnerDolphin(dolphinName, energy);
                default -> throw new IllegalArgumentException(INVALID_DOLPHIN_TYPE);
            };

            boolean dolphinNameMatches = pools.values().stream()
                    .anyMatch(p -> p.getDolphins()
                            .stream()
                            .anyMatch(d -> d.getName().equals(dolphinName)));

            if (dolphinNameMatches) {
                throw new IllegalArgumentException(DOLPHIN_EXISTS);
            }

            Pool pool = pools.get(poolName);
            String poolType = pool.getClass().getSimpleName();

            boolean canLiveInPool = true;

            if (dolphinType.equals("BottleNoseDolphin") && !poolType.equals("DeepWaterPool")) {
                canLiveInPool = false;
            } else if (dolphinType.equals("SpinnerDolphin") && !poolType.equals("ShallowWaterPool")) {
                canLiveInPool = false;
            }

            if (!canLiveInPool) {
                return POOL_NOT_SUITABLE;
            }

            pool.addDolphin(dolphin);

            return String.format(SUCCESSFULLY_ADDED_DOLPHIN_IN_POOL, dolphinType, dolphinName, poolName);
        }

        @Override
        public String feedDolphins(String poolName, String foodType) {
            Pool pool = pools.get(poolName);
            Food food = pool.getFoods().stream()
                    .filter(f -> f.getClass().getSimpleName().equals(foodType))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException(NO_FOOD_OF_TYPE_ADDED_TO_POOL));

            pool.getFoods().remove(food);

            Collection<Dolphin> dolphins = pool.getDolphins();
            dolphins.forEach(d -> d.eat(food));
            return String.format(DOLPHINS_FED, dolphins.size(), poolName);
        }

        @Override
        public String playWithDolphins(String poolName) {
            Pool pool = pools.get(poolName);

            Collection<Dolphin> dolphins = pool.getDolphins();
            if (dolphins.isEmpty()) {
                throw new IllegalArgumentException(NO_DOLPHINS);
            }

            int exhaustedDolphins = 0;
            for (Dolphin dolphin : dolphins) {
                dolphin.jump();
                if (dolphin.getEnergy() <= 0) {
                    exhaustedDolphins++;
                }
            }
            pool.getDolphins().removeIf(d -> d.getEnergy() <= 0);
            return String.format(DOLPHINS_PLAY, poolName, exhaustedDolphins);
        }

        @Override
        public String getStatistics() {
            return pools.values()
                    .stream()
                    .map(this::formatPoolStatistics)
                    .collect(Collectors.joining(System.lineSeparator()));
        }

        private String formatPoolStatistics(Pool pool) {
            String dolphinInfo = pool.getDolphins().stream()
                    .map(d -> d.getName() + " - " + d.getEnergy())
                    .collect(Collectors.joining(DELIMITER));

            dolphinInfo = dolphinInfo.isEmpty() ? NONE : dolphinInfo;

            return String.format(DOLPHINS_FINAL, pool.getName()) + System.lineSeparator() + dolphinInfo;
        }*/

    private FoodRepositoryImpl foodRepository;
    private Collection<Pool> pools;
    //private Map<String, Pool> pools;


    public ControllerImpl() {
        this.foodRepository = new FoodRepositoryImpl();
        this.pools = new ArrayList<>();
        //this.pools = new LinkedHashMap<>();
    }

    @Override
    public String addPool(String poolType, String poolName) {
        if (!(poolType.equals("DeepWaterPool") || poolType.equals("ShallowWaterPool"))) {
            throw new NullPointerException(INVALID_POOL_TYPE);
        }

        Pool poolByName = getPoolByName(poolName);

        if (poolByName != null) {
            throw new NullPointerException(POOL_EXISTS);
        }

        Pool pool;
        switch (poolType) {
            case "DeepWaterPool":
                pool = new DeepWaterPool(poolName);
                pools.add(pool);
                break;
            case "ShallowWaterPool":
                pool = new ShallowWaterPool(poolName);
                pools.add(pool);
                break;

        }
        return String.format(SUCCESSFULLY_ADDED_POOL_TYPE, poolType, poolName);
    }
   /* @Override
    public String addPool(String poolType, String poolName) {
        Pool pool = switch (poolType) {
            case "DeepWaterPool" -> new DeepWaterPool(poolName);
            case "ShallowWaterPool" -> new ShallowWaterPool(poolName);
            default -> throw new NullPointerException(INVALID_POOL_TYPE);
        };

        if (pools.containsKey(pool.getName())) {
            throw new NullPointerException(POOL_EXISTS);
        }

        pools.put(pool.getName(), pool);

        return String.format(SUCCESSFULLY_ADDED_POOL_TYPE, poolType, poolName);
    }*/

    @Override
    public String buyFood(String foodType) {

        if (!(foodType.equals("Squid") || foodType.equals("Herring") || foodType.equals("Mackerel"))) {
            throw new IllegalArgumentException(INVALID_FOOD_TYPE);
        }
        Food food;
        switch (foodType) {
            case "Squid":
                food = new Squid();
                foodRepository.add(food);
                break;
            case "Herring":
                food = new Herring();
                foodRepository.add(food);
                break;
            case "Mackerel":
                food = new Mackerel();
                foodRepository.add(food);
                break;
        }
//        foodRepository.getFoods().forEach(f -> System.out.println(f));
//        System.out.println(foodRepository.getFoodsCount());
        return String.format(SUCCESSFULLY_BOUGHT_FOOD_TYPE, foodType);
    }
  /*  @Override
    public String buyFood(String foodType) {
        Food food = switch (foodType) {
            case "Herring" -> new Herring();
            case "Mackerel" -> new Mackerel();
            case "Squid" -> new Squid();
            default -> throw new IllegalArgumentException(INVALID_FOOD_TYPE);
        };
        foodRepository.add(food);
        return String.format(SUCCESSFULLY_BOUGHT_FOOD_TYPE, foodType);
    }*/

    @Override
    public String addFoodToPool(String poolName, String foodType) {

        Pool pool = getPoolByName(poolName);
        Food food = foodRepository.findByType(foodType);
        if (food == null) {
            throw new IllegalArgumentException(String.format(NO_FOOD_FOUND, foodType));
        }
        pool.addFood(food);
        foodRepository.remove(food);
//        System.out.println(pool.getFoods().size());
//        pool.getFoods().forEach(f -> System.out.println(f.getClass().getSimpleName()));
        return String.format(SUCCESSFULLY_ADDED_FOOD_IN_POOL, foodType, poolName);
    }
    /*@Override
    public String addFoodToPool(String poolName, String foodType) {
        Food food = foodRepository.findByType(foodType);
        if (food == null) {
            throw new IllegalArgumentException(String.format(NO_FOOD_FOUND, foodType));
        }

        Pool pool = pools.get(poolName);
        pool.addFood(food);
        return String.format(SUCCESSFULLY_ADDED_FOOD_IN_POOL, foodType, poolName);
    }*/

    @Override
    public String addDolphin(String poolName, String dolphinType, String dolphinName, int energy) {
        Dolphin dolphin;

        Pool pool = getPoolByName(poolName);

        for (Dolphin d : pool.getDolphins()) {
            if (d.getName().equals(dolphinName)) {
                throw new IllegalArgumentException(DOLPHIN_EXISTS);
            }
        }

        switch (dolphinType) {
            case "BottleNoseDolphin":
                dolphin = new BottleNoseDolphin(dolphinName, energy);
                break;
            case "SpottedDolphin":
                dolphin = new SpottedDolphin(dolphinName, energy);
                break;
            case "SpinnerDolphin":
                dolphin = new SpinnerDolphin(dolphinName, energy);
                break;
            default:
                throw new IllegalArgumentException(INVALID_DOLPHIN_TYPE);
        }

        if (pool.getClass().getSimpleName().equals("DeepWaterPool")
                && dolphinType.equals("BottleNoseDolphin")
                || dolphinType.equals("SpottedDolphin")) {
            pool.addDolphin(dolphin);
        } else if (pool.getClass().getSimpleName().equals("ShallowWaterPool")
                && dolphinType.equals("SpinnerDolphin")) {
            pool.addDolphin(dolphin);
        } else {
            return POOL_NOT_SUITABLE;
        }
        return String.format(SUCCESSFULLY_ADDED_DOLPHIN_IN_POOL, dolphinType, dolphinName, poolName);
    }
   /* @Override
    public String addDolphin(String poolName, String dolphinType, String dolphinName, int energy) {
        Dolphin dolphin = switch (dolphinType) {
            case "BottleNoseDolphin" -> new BottleNoseDolphin(dolphinName, energy);
            case "SpottedDolphin" -> new SpottedDolphin(dolphinName, energy);
            case "SpinnerDolphin" -> new SpinnerDolphin(dolphinName, energy);
            default -> throw new IllegalArgumentException(INVALID_DOLPHIN_TYPE);
        };

        boolean dolphinNameMatches = pools.values().stream()
                .anyMatch(p -> p.getDolphins()
                        .stream()
                        .anyMatch(d -> d.getName().equals(dolphinName)));

        if (dolphinNameMatches) {
            throw new IllegalArgumentException(DOLPHIN_EXISTS);
        }

        Pool pool = pools.get(poolName);
        String poolType = pool.getClass().getSimpleName();

        boolean canLiveInPool = true;

        if (dolphinType.equals("BottleNoseDolphin") && !poolType.equals("DeepWaterPool")) {
            canLiveInPool = false;
        } else if (dolphinType.equals("SpinnerDolphin") && !poolType.equals("ShallowWaterPool")) {
            canLiveInPool = false;
        }

        if (!canLiveInPool) {
            return POOL_NOT_SUITABLE;
        }

        pool.addDolphin(dolphin);

        return String.format(SUCCESSFULLY_ADDED_DOLPHIN_IN_POOL, dolphinType, dolphinName, poolName);
    }*/

    @Override
    public String feedDolphins(String poolName, String foodType) {

        Pool pool = getPoolByName(poolName);

        Food currentFood = pool.getFoods()
                .stream()
                .filter(f -> f.getClass().getSimpleName().equals(foodType))
                .findFirst()
                .orElse(null);

        if (currentFood == null) {
            throw new IllegalArgumentException(NO_FOOD_OF_TYPE_ADDED_TO_POOL);
        }
        for (Dolphin dolphin : pool.getDolphins()) {
            dolphin.eat(currentFood);
        }

        pool.getFoods().remove(currentFood);

        return String.format(DOLPHINS_FED, pool.getDolphins().size(), pool.getName());
    }
    /*@Override
    public String feedDolphins(String poolName, String foodType) {
        Pool pool = pools.get(poolName);
        Food food = pool.getFoods().stream()
                .filter(f -> f.getClass().getSimpleName().equals(foodType))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NO_FOOD_OF_TYPE_ADDED_TO_POOL));

        pool.getFoods().remove(food);

        Collection<Dolphin> dolphins = pool.getDolphins();
        dolphins.forEach(d -> d.eat(food));
        return String.format(DOLPHINS_FED, dolphins.size(), poolName);
    }*/

    @Override
    public String playWithDolphins(String poolName) {

        Pool pool = getPoolByName(poolName);

        int removed = 0;
        if (pool.getDolphins().isEmpty()) {
            throw new IllegalArgumentException(NO_DOLPHINS);
        }


        Iterator<Dolphin> dolphins = pool.getDolphins().iterator();

        while (dolphins.hasNext()) {
            Dolphin dolphin = dolphins.next();
            dolphin.jump();

            if (dolphin.getEnergy() <= 0) {
                dolphins.remove();
                removed++;
            }
        }
        return String.format(DOLPHINS_PLAY, poolName, removed);
    }
   /* @Override
    public String playWithDolphins(String poolName) {
        Pool pool = pools.get(poolName);

        Collection<Dolphin> dolphins = pool.getDolphins();
        if (dolphins.isEmpty()) {
            throw new IllegalArgumentException(NO_DOLPHINS);
        }

        int exhaustedDolphins = 0;
        for (Dolphin dolphin : dolphins) {
            dolphin.jump();
            if (dolphin.getEnergy() <= 0) {
                exhaustedDolphins++;
            }
        }
        pool.getDolphins().removeIf(d -> d.getEnergy() <= 0);
        return String.format(DOLPHINS_PLAY, poolName, exhaustedDolphins);
    }*/

    @Override
    public String getStatistics() {
        StringBuilder build = new StringBuilder();

        for (Pool pool : pools) {
            build.append(String.format(DOLPHINS_FINAL, pool.getName()))
                    .append(System.lineSeparator());

            if (pool.getDolphins().isEmpty()) {
                build.append(NONE).append(System.lineSeparator());
            } else {
                build.append(pool.getDolphins().stream().map(dolphin -> dolphin.getName() + " - " + dolphin.getEnergy()).collect(Collectors.joining(DELIMITER)))
                        .append(System.lineSeparator());
            }
        }
        return build.toString().trim();
    }

    public Pool getPoolByName(String pollName) {
        return pools.stream()
                .filter(a -> a.getName().equals(pollName))
                .findFirst()
                .orElse(null);
    }
    /*@Override
    public String getStatistics() {
        return pools.values()
                .stream()
                .map(this::formatPoolStatistics)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    private String formatPoolStatistics(Pool pool) {
        String dolphinInfo = pool.getDolphins().stream()
                .map(d -> d.getName() + " - " + d.getEnergy())
                .collect(Collectors.joining(DELIMITER));

        dolphinInfo = dolphinInfo.isEmpty() ? NONE : dolphinInfo;

        return String.format(DOLPHINS_FINAL, pool.getName()) + System.lineSeparator() + dolphinInfo;
    }*/
}
