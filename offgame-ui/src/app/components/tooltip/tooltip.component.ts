import {Component, ElementRef, Input, Renderer2, ViewChild} from '@angular/core';
import {NgForOf, NgIf} from "@angular/common";

@Component({
    selector: 'text-tooltip',
    standalone: true,
    imports: [
        NgForOf,
        NgIf
    ],
    templateUrl: '../../../templates/text-tooltip.html',
    styleUrl: '../../../styles/styles.scss'
})
export class TooltipComponent {

    @Input() text!: string;
    @ViewChild('tooltip', {static: true}) tooltip : ElementRef<HTMLDivElement> | undefined;

    constructor(private renderer: Renderer2) {
    }

    showTooltip() {
        this.renderer.setStyle(this.tooltip?.nativeElement, 'visibility', 'visible');
    }

    moveTooltip(newX: number, newY: number): void {
        let offset = 10;
        let x = newX + offset;
        let y = newY + offset;
        this.renderer.setStyle(this.tooltip?.nativeElement, 'left', x + 'px');
        this.renderer.setStyle(this.tooltip?.nativeElement, 'top', y + 'px');
    }

    hideTooltip(): void {
        this.renderer.setStyle(this.tooltip?.nativeElement, 'visibility', 'hidden');
    }
}
