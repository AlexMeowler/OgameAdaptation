export class Resources {
    metal!: bigint
    crystal!: bigint
    deuterium!: bigint
    energy?: bigint | null

    constructor(data: Resources) {
        Object.assign(this, data);
    }
}