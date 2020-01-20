import { Component, OnInit, Input, NgModule } from '@angular/core';
// import { LoggerService } from './logger.service';
import * as _ from 'lodash';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent implements OnInit {
  public title: string;
  private greeting: string;
  @Input() private isShowDetail: boolean;
  private msgToChild: string;
  private msgFromChild: string;


  // constructor(private logger: LoggerService) {}
  constructor() {
    this.title = 'my-app1';
  }
  ngOnInit(): void {
    this.isShowDetail = true;
    this.greeting = 'angular 2';
    const aa = '' + 'aaa';
    // this.logger.debug('根组件已经初始化完毕666');
    this.msgToChild = 'message from parent';

    const data = '---foo--bar--foo-bar';
  }

  receive(msg: string) {
    this.msgFromChild = msg;
  }
}
