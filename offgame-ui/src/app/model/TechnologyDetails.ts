import {Requirement} from "./Requirement";

export class TechnologyDetails {

    description!: string;
    imageName!: string;
    name!: string;
    requirements: Requirement[];

    constructor(data: any) {
        Object.assign(this, data);

        this.requirements = data.requirements.map((object: any) => new Requirement(object))
    }
}