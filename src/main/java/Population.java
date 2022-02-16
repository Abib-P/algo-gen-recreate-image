import java.awt.image.BufferedImage;
import java.util.*;
import java.util.stream.Collectors;

public class Population {
    private List<Individual> population;
    private final PolygonFactory polygonFactory;
    private final BufferedImage originalImage;
    private final ImageComparator imageComparator;

    public BufferedImage getOriginalImage() {
        return originalImage;
    }

    public Population(PolygonFactory polygonFactory, BufferedImage originalImage, ImageComparator imageComparator) {
        this.polygonFactory = polygonFactory;
        this.imageComparator = imageComparator;
        this.population = new ArrayList<>();
        this.originalImage = originalImage;
    }

    public void initPopulation(int nbOfIndividual, int nbOfPolygon) {
        population = new ArrayList<>();
        for (int i = 0; i < nbOfIndividual; i++) {
            List<Polygonal> polygonals = new ArrayList<>();
            for (int j = 0; j < nbOfPolygon; j++) {
                polygonals.add(polygonFactory.createTriangle());
            }
            population.add(new Individual(polygonals, polygonFactory.getMaxWidth().intValue(), polygonFactory.getMaxHeight().intValue()));
        }

        population.forEach(Individual::drawItself);
        comparePopulationToOriginalImage();
    }

    public Individual getBestOfThisGeneration() {
        return population.stream().reduce((element1, element2) -> {
            if (element1.getErrorPercentage() < element2.getErrorPercentage()) {
                return element1;
            }
            return element2;
        }).get();
    }

    public void startNewRound() {
        population = selection();
        crossPopulation();
        population.forEach(Individual::drawItself);
        comparePopulationToOriginalImage();
        System.out.println(population.size());
    }

    private void comparePopulationToOriginalImage() {
        population.forEach(x -> {
            x.setErrorPercentage(imageComparator.compareImage(originalImage, x.getBufferedImage()));
        });
    }

    private List<Individual> selection() {
        population.sort(Comparator.comparingDouble(Individual::getErrorPercentage));
        Collections.sort(population);
        return population.subList(0,population.size()/2);
    }

    private void crossPopulation() {
        List<Individual> tempIndividuals = new ArrayList<>(population);
        while (tempIndividuals.size() > 1) {
            Random rand = new Random();
            Individual first = tempIndividuals.get(rand.nextInt(tempIndividuals.size()));
            tempIndividuals.remove(first);
            Individual second = tempIndividuals.get(rand.nextInt(tempIndividuals.size()));
            tempIndividuals.remove(second);

            population.addAll(crossIndividual(first, second));
        }
    }

    private List<Individual> crossIndividual(Individual first, Individual second) {
        List<Polygonal> firstFirstHalf = first.getPolygons().subList(0,first.getPolygons().size()/2);
        List<Polygonal> firstSecondHalf = first.getPolygons().subList(first.getPolygons().size()/2,first.getPolygons().size());
        List<Polygonal> secondFirstHalf = second.getPolygons().subList(0,second.getPolygons().size()/2);
        List<Polygonal> secondSecondHalf = second.getPolygons().subList(second.getPolygons().size()/2,second.getPolygons().size());

        List<Polygonal> individual1Poly = new ArrayList<>(firstFirstHalf);
        individual1Poly.addAll(secondSecondHalf);
        List<Polygonal> individual2Poly = new ArrayList<>(secondFirstHalf);
        individual2Poly.addAll(firstSecondHalf);

        Individual individual1 = new Individual(individual1Poly,first.getBufferedImage().getWidth(),first.getBufferedImage().getHeight());
        Individual individual2 = new Individual(individual2Poly,first.getBufferedImage().getWidth(),first.getBufferedImage().getHeight());

        return List.of(individual1,individual2);
    }

}
