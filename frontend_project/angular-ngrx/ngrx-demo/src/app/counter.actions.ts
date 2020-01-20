import { createAction, props } from '@ngrx/store';
import { State, TitleState } from './counter.selector';

export const increment = createAction('[Counter Component] Increment');
export const decrement = createAction('[Counter Component] Decrement');
export const reset = createAction('[Counter Component] Reset');

export const hello = createAction('[Hello Service] call', props<TitleState>());
export const helloSuccess = createAction('[Hello Service] call success', props<TitleState>());