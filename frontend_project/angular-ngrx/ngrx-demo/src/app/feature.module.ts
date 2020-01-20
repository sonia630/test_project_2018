import { NgModule } from '@angular/core';
import { StoreModule } from '@ngrx/store';
import { counterReducer } from './counter.reducer';
import { metaReducers } from './pre.reducer';
import { featureKey } from './feature.enum';
import { HelloEffects } from './hello.effects';

@NgModule({
  imports: [
    StoreModule.forFeature(featureKey, counterReducer ),
  ],
})
export class ScoreboardModule {}