package exampreparation.christmasPastryShop;


import christmasPastryShop.core.ControllerImpl;
import christmasPastryShop.core.EngineImpl;
import christmasPastryShop.core.interfaces.Controller;
import christmasPastryShop.core.interfaces.Engine;
import christmasPastryShop.entities.booths.interfaces.Booth;
import christmasPastryShop.entities.delicacies.interfaces.Delicacy;
import christmasPastryShop.entities.drinks.interfaces.Cocktail;
import christmasPastryShop.io.ConsoleReader;
import christmasPastryShop.io.ConsoleWriter;
import exampreparation.christmasPastryShop.repositories.BoothRepositoryImpl;
import exampreparation.christmasPastryShop.repositories.CocktailRepositoryImpl;
import exampreparation.christmasPastryShop.repositories.DelicacyRepositoryImpl;
import exampreparation.christmasPastryShop.repositories.interfaces.BoothRepository;
import exampreparation.christmasPastryShop.repositories.interfaces.CocktailRepository;
import exampreparation.christmasPastryShop.repositories.interfaces.DelicacyRepository;

public class Main {
    public static void main(String[] args) {

        String a = " ";
        int a1 = a.length();
        DelicacyRepository<Delicacy> delicacyRepository = new DelicacyRepositoryImpl(); // TODO: new DelicacyRepositoryImpl<>();
        CocktailRepository<Cocktail> cocktailRepository = new CocktailRepositoryImpl(); // TODO: new CocktailRepositoryImpl<>();
        BoothRepository<Booth> boothRepository = new BoothRepositoryImpl(); // TODO: new BoothRepositoryImpl<>();

        Controller controller = new ControllerImpl(delicacyRepository, cocktailRepository, boothRepository); // TODO: new ControllerImpl(delicacyRepository, cocktailRepository, boothRepository);

        // TODO:OPTIONAL

        ConsoleReader reader = new ConsoleReader();
        ConsoleWriter writer = new ConsoleWriter();
        EngineImpl engine = new EngineImpl(reader, writer, controller);
        engine.run();

    }
}
