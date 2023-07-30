package fr.esgi.gameforgeapi.bootstrap;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.junit.CacheMode.FOREVER;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;
import static fr.esgi.gameforgeapi.bootstrap.PackagesAndLayers.*;

@AnalyzeClasses(
        packages = "fr.esgi.gameforgeapi",
        cacheMode = FOREVER)
public class LayersDevelopmentRulesTest {

    @ArchTest
    public static final ArchRule LAYERS_DEVELOPMENT_RULE =
            layeredArchitecture()
                    .layer(DOMAIN_LAYER)
                    .definedBy(DOMAIN_PACKAGE)// utiliser un objet class.getBasePackage() plutôt que hardcoder les packages
                    .layer(BOOTSTRAP_LAYER)
                    .definedBy(BOOTSTRAP_PACKAGE)
                    .layer(CLIENT_LAYER)
                    .definedBy(CLIENT_PACKAGE)
                    .layer(SERVER_LAYER)
                    .definedBy(SERVER_PACKAGE)
                    .whereLayer(DOMAIN_LAYER)
                    .mayOnlyBeAccessedByLayers(BOOTSTRAP_LAYER, CLIENT_LAYER, SERVER_LAYER)
                    .whereLayer(BOOTSTRAP_LAYER)
                    .mayOnlyBeAccessedByLayers(CLIENT_LAYER)
                    .whereLayer(CLIENT_LAYER)
                    .mayOnlyBeAccessedByLayers(BOOTSTRAP_LAYER)
                    .whereLayer(SERVER_LAYER)
                    .mayOnlyBeAccessedByLayers(BOOTSTRAP_LAYER)
                    .because("That's the main and most important hexagonal architecture rule !");
}