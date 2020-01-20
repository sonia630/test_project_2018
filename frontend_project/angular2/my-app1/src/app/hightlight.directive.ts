import { Directive, ElementRef, Renderer } from '@angular/core';

@Directive({
    selector: '[appLight]',
})
export class HightLightDirective {
    constructor(el: ElementRef, render: Renderer) {
        render.setElementStyle(el.nativeElement, 'backgroundColor', 'yellow');
    }
}
