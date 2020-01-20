import { ActionReducer, MetaReducer } from '@ngrx/store';

export function debug(reducer: ActionReducer<any>): ActionReducer<any> {
    return function( state, action) {
      console.log('pre state', state);
      console.log('pre action', action);
      return reducer(state, action);
    };
}
  export const metaReducers: MetaReducer<any>[] = [debug];