export class UtilService {

    static calcGradient(weight: number, inverse?: boolean) {
        let colorA = {
            red: 0,
            green: 255,
            blue: 0
        }
        let colorB = {
            red: 255,
            green: 0,
            blue: 0
        }
        let w1 = inverse ? weight : 1 - weight ;
        let w2 = 1 - w1
        let result = {
            red: Math.round(colorA.red * w1 + colorB.red * w2),
            green: Math.round(colorA.green * w1 + colorB.green * w2),
            blue: Math.round(colorA.blue * w1 + colorB.blue * w2)
        }
        return `rgb(${result.red}, ${result.green}, ${result.blue})`
    }
}