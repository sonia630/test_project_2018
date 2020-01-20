import { createSelector, createFeatureSelector } from '@ngrx/store';
import { featureKey } from './feature.enum';

export interface AppState {
    [featureKey]: State;
 }
export interface State {
    count: number;
}
export interface TitleState {
    title: string;
    name?: string;
}
export const appStateFn = (state: AppState) => {
    console.log('app state....', state);
    return state[featureKey];
};

export const countStateFn = (state: State, props) => {
    console.log('count state....', state);
    return  state.count * props.multiply;
};

export const titleStateFn = (state: TitleState) => {
    console.log('title state....', state);
    return state.title;
};

export const countFeature = createFeatureSelector<AppState, State>(featureKey);
export const titleFeature = createFeatureSelector<AppState, TitleState>(featureKey);


// logic selector
export const countStateSelector = createSelector(appStateFn, countStateFn);
export const titleStateSelector = createSelector(titleFeature, titleStateFn);