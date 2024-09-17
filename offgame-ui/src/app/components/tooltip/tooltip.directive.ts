import {
    AfterViewInit,
    ComponentRef,
    Directive,
    ElementRef,
    HostListener,
    Input,
    Renderer2,
    ViewContainerRef
} from '@angular/core';
import {TooltipComponent} from "./tooltip.component";

@Directive({
    selector: '[text-tooltip]',
    standalone: true
})
export class TooltipDirective implements AfterViewInit {

    @Input() tooltipText! : string;

    private readonly tooltip : ComponentRef<TooltipComponent>;

    constructor(private elementRef: ElementRef,
                private renderer: Renderer2,
                private viewContainerRef: ViewContainerRef) {

        this.tooltip = this.viewContainerRef.createComponent(TooltipComponent);
    }

    ngAfterViewInit(): void {
        this.tooltip.setInput("text", this.tooltipText);
    }

    @HostListener("mouseover")
    showTooltip() {
        this.tooltip.instance.showTooltip();
    }

    @HostListener("mousemove", ['$event'])
    moveTooltip(event: MouseEvent): void {
        this.tooltip.instance.moveTooltip(event.layerX, event.layerY);
    }

    @HostListener("mouseout")
    hideTooltip(): void {
        this.tooltip.instance.hideTooltip();
    }
}
