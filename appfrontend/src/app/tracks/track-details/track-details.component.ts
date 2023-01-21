import { Component, OnInit } from '@angular/core';
import {Track} from "../../zshared/interfaces/track";
import {TracksService} from "../../zshared/services/tracks.service";
import {ActivatedRoute, Params, Router} from "@angular/router";
import {tap} from "rxjs";

@Component({
  selector: 'app-track-details',
  templateUrl: './track-details.component.html',
  styleUrls: ['./track-details.component.css']
})
export class TrackDetailsComponent implements OnInit {
  track : Track;
  id : number;

  constructor(private tracksService: TracksService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit(): void {
    this.route.params
      .subscribe(
        (param: Params) => {
          this.id = +param['id'];
          this.tracksService.getTrack(this.id)
            .pipe(tap(
                (track: Track) => {
                  this.track = track;})
            ).subscribe();
        }
      );
  }

  onAddToProject() {
    // TODO:

  }

  onEditTrack() {
    this.router.navigate(
      ['edit'],
      {relativeTo: this.route}
    );
  }

  onDeleteTrack() {
    this.tracksService.removeTrack(this.id)
      .subscribe();
    this.router.navigate(
      ['../'],
      {relativeTo: this.route}
    );
  }
}
