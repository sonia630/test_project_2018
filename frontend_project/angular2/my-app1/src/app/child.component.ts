import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

@Component({
    selector: 'app-child',
    templateUrl: './child.component.html'
})

export class ChildComponent implements OnInit {
    @Input() public message: string;
    @Output() private outer = new EventEmitter<string>();
    constructor() { }

    ngOnInit(): void { }

    sendToParent() {
        this.outer.emit('88 message from child 666');
    }
}
