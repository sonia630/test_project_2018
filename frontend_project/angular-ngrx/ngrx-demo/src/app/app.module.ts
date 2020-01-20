import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { StoreModule } from '@ngrx/store';
import { counterReducer } from './counter.reducer';
import { MyCounterComponent } from './my-counter/my-counter.component';
import { ScoreboardModule } from './feature.module';
import { metaReducers } from './pre.reducer';
import { EffectsModule } from '@ngrx/effects';
import { HelloEffects } from './hello.effects';

@NgModule({
  declarations: [
    AppComponent,
    MyCounterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    StoreModule.forRoot({}),
    ScoreboardModule,
    EffectsModule.forRoot([HelloEffects]),
    // EffectsModule.forFeature([HelloEffects]),
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
