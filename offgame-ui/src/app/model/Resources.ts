export class Resources {
    public metal!: bigint
    public crystal!: bigint
    public deuterium!: bigint
    public energy?: bigint | null

    constructor(data: Resources) {
        Object.assign(this, data);
    }
}