import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { EMPTY } from 'rxjs';
import { map, mergeMap, catchError } from 'rxjs/operators';
import { HelloService } from './hello.service';
import { increment, decrement, reset, hello, helloSuccess } from './counter.actions';

@Injectable()
export class HelloEffects {

  loadMovies$ = createEffect(() => this.actions$.pipe(
    ofType(hello),
    mergeMap((action) => this.helloService.getHello(action)
      .pipe(
        map((movies: string) => {
          console.log('in effect map...', movies);
          return helloSuccess({title: movies});
        }),
        catchError(() => EMPTY)
      )
      )
    )
  );

  constructor(
    private actions$: Actions,
    private helloService: HelloService
  ) {}
}