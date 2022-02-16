import java.util.Random;

public class Mutation {
    private final Integer mutationChance;
    private final Integer mutationAmount;

    public Mutation(Integer mutationChance, Integer mutationAmount) {
        this.mutationChance = mutationChance;
        this.mutationAmount = mutationAmount;
    }

    public Individual mutate(Individual individual) {
        Random random = new Random();
        if (random.nextInt(100) <= mutationChance) {
            switch (random.nextInt(2)) {
                case 0 -> { //changer polygon position
                    switch  (random.nextInt(3)) {
                        case 0 -> { // x1 et y1
                            Polygonal polygonal = individual.getPolygons().get(random.nextInt( individual.getPolygons().size()));
                            polygonal.getPolygon() = 
                        }
                        case 1 -> { // x2 et y2

                        }
                        case 2 -> { // x3 et y3

                        }
                    }
                }
                case 1 -> { //changer polygon color
                    switch (random.nextInt(4)) {
                        case 0 -> { // red

                        }
                        case 1 -> { // green

                        }
                        case 2 -> { // blue

                        }
                        case 3 -> { // alpha

                        }
                    }
                }
            }
        }
    }
}
