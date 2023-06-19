package bucheon.leafy.domain.search;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FlowerGubn {

    CUT_FLOWERS("절화"), FOLIAGE("관엽"), ORCHID("난"), CYMBIDIUM_GOERINGII("춘란");

    private final String flowerGubn;

}
