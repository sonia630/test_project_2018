import { Component, OnInit } from '@angular/core';
import { Store, select, createSelector, createFeatureSelector } from '@ngrx/store';
import { Observable } from 'rxjs';
import { increment, decrement, reset } from '../counter.actions';
import { countStateSelector, titleStateSelector, State, AppState } from '../counter.selector';
import { HelloService } from '../hello.service';
import { hello } from '../counter.actions';

@Component({
  selector: 'app-my-counter',
  templateUrl: './my-counter.component.html',
  styleUrls: ['./my-counter.component.scss'],
})
export class MyCounterComponent implements OnInit {

  count$: Observable<number>;
  title$: Observable<string>;

  constructor(private store: Store<AppState>) {
    this.count$ = store.pipe(select(countStateSelector, { multiply: 2}));
    this.title$ = store.pipe(select(titleStateSelector));
  }

  ngOnInit(): void {
    this.store.dispatch(hello({title: 'hello dispatch', name: 'ngrx '}));
  }

  increment() {
    console.log('counter dispatch increment...');
    this.store.dispatch(increment());
  }

  decrement() {
    this.store.dispatch(decrement());
  }

  reset() {
    this.store.dispatch(reset());
  }
}