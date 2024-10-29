import {Resources} from "./Resources";
import {ResourceDetail} from "./ResourceDetail";

export class ResourcesDetails {

    planetName!: string
    resourceDetails: ResourceDetail[] = []
    totalResources: Resources

    constructor(data: any) {
        Object.assign(this, data);

        this.resourceDetails = data.resourceDetails.map((object: any) => new ResourceDetail(object))

        this.totalResources = new Resources(data.totalResources)
        this.totalResources.initResourceLogic()
    }
}