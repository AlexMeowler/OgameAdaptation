import {
    AfterViewInit,
    ApplicationRef,
    ComponentRef,
    Directive,
    HostListener,
    Input,
    ViewContainerRef
} from '@angular/core';
import {TooltipComponent} from "./tooltip.component";

@Directive({
    selector: '[text-tooltip]',
    standalone: true
})
export class TooltipDirective implements AfterViewInit {

    @Input() tooltipText! : string;

    private tooltip! : ComponentRef<TooltipComponent>;

    constructor(private applicationRef: ApplicationRef) {
    }

    ngAfterViewInit(): void {
        let rootContainerRef = this.applicationRef.components[0].injector.get(ViewContainerRef)
        this.tooltip = rootContainerRef.createComponent(TooltipComponent);
        this.tooltip.changeDetectorRef.detectChanges();
        this.tooltip.setInput("text", this.tooltipText);
    }

    @HostListener("mouseover")
    showTooltip() {
        this.tooltip.instance.showTooltip();
    }

    @HostListener("mousemove", ['$event'])
    moveTooltip(event: MouseEvent): void {
        this.tooltip.instance.moveTooltip(event.pageX, event.pageY);
    }

    @HostListener("mouseout")
    hideTooltip(): void {
        this.tooltip.instance.hideTooltip();
    }
}
