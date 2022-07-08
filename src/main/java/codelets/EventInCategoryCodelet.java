package codelets;

import br.unicamp.cst.representation.idea.Idea;
import entities.EventTrackerPropertyCategoryCodelet;
import entities.PropertyCategory;
import pheromone.Position;

import java.util.List;

public class EventInCategoryCodelet extends EventTrackerPropertyCategoryCodelet {
    PropertyCategory propertyCategory;

    public EventInCategoryCodelet(PropertyCategory propertyCategory)   {
        super();
        this.propertyCategory = propertyCategory;
        this.setName(propertyCategory.getName()+" - IN");
    }

    @Override
    public boolean eventTracked() {
        if(this.propertyCategory.belongsToCategory(getInitialPosition()) == false &&
           this.propertyCategory.belongsToCategory(getFinalPosition()) == true) {
            return true;
        }
        return false;

    }

    public Position getInitialPosition() {
        List<Idea> stateIdeaList = (List<Idea>) ((Idea) this.objectsBufferMO.getI()).get("timeSteps").getValue();
        int initialPositionIdx = stateIdeaList.size()-1;

        Position initialPosition = getNthPosition(initialPositionIdx);

        return initialPosition;
    }

    public Position getFinalPosition() {
        int finalPositionIdx = 0;

        Position finalPosition = getNthPosition(finalPositionIdx);

        return finalPosition;
    }

    public Position getNthPosition(int nthPositionIdx) {
        List<Idea> stateIdeaList = (List<Idea>) ((Idea) this.objectsBufferMO.getI()).get("timeSteps").getValue();
        Idea initialStateIdea = (Idea) stateIdeaList.get(nthPositionIdx);

        double nthLatitude = (double) initialStateIdea.get("latitude").getValue();
        double nthLongitude = (double) initialStateIdea.get("longitude").getValue();

        Position nthPosition = new Position(nthLatitude, nthLongitude);

        return nthPosition;

    }
}