import {
    ComponentRef,
    Directive,
    ElementRef,
    HostListener,
    Input,
    OnInit,
    Renderer2,
    ViewContainerRef
} from '@angular/core';
import {TooltipComponent} from "./tooltip.component";

@Directive({
    selector: '[text-tooltip]',
    standalone: true
})
export class TooltipDirective implements OnInit {

    @Input() tooltipText! : string;

    private readonly tooltip : ComponentRef<TooltipComponent>;

    constructor(private elementRef: ElementRef,
                private renderer: Renderer2,
                private viewContainerRef: ViewContainerRef) {

        this.tooltip = this.viewContainerRef.createComponent(TooltipComponent);
    }

    ngOnInit(): void {
        this.tooltip.setInput("text", this.tooltipText);
    }

    @HostListener("mouseover")
    showTooltip() {
        this.tooltip.instance.showTooltip();
    }

    @HostListener("mousemove", ['$event'])
    moveTooltip(event: MouseEvent): void {
        this.tooltip.instance.moveTooltip(event.clientX, event.clientY);
    }

    @HostListener("mouseout")
    hideTooltip(): void {
        this.tooltip.instance.hideTooltip();
    }
}
