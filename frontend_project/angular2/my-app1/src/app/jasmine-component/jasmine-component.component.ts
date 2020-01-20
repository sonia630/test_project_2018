import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-jasmine-component',
  templateUrl: './jasmine-component.component.html',
  styleUrls: ['./jasmine-component.component.css'],
})
export class JasmineComponentComponent implements OnInit {
  public totalVotes = 0;
  constructor() {}

  ngOnInit() {}

  public calculate(input) {
    // if(x)
  }

  public compute(inPutNumber: number) {
    if (inPutNumber < 0) {
      return 0;
    }
    return inPutNumber + 1;
  }

  public greet(msg: string): string {
    return 'Welcome ' + msg;
  }

  public arr(): any[] {
    return ['usa', 'aud', 'eur'];
  }

  upVote() {
    this.totalVotes++;
  }
  downVote() {
    this.totalVotes--;
  }
}
