import {Component, OnInit} from '@angular/core';
import {TracksService} from "../../zshared/services/tracks.service";
import {ActivatedRoute, Params, Router} from "@angular/router";
import {Track} from "../../zshared/interfaces/track";
import {FormArray, FormControl, FormGroup} from "@angular/forms";
import {tap} from "rxjs/operators";
import { Artist } from 'src/app/zshared/interfaces/artist';

@Component({
  selector: 'app-track-edit',
  templateUrl: './track-edit.component.html',
  styleUrls: ['./track-edit.component.css']
})
export class TrackEditComponent implements OnInit {
  track: Track;
  id: number;
  editMode = false;
  trackForm: FormGroup;


  constructor(private tracksService: TracksService,
              private route: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit(): void {
    this.route.params
      .subscribe(
        (param: Params) => {
          this.id = +param['id'];
          this.editMode = param['id'] != null;
          this.initForm();
        }
      );
  }

  private async initForm() {
    let trackId: number;
    let trackName = '';
    let trackImageUrl = '';
    let trackUrl = '';
    let trackLength: number;

    let artist = [];

    if (this.editMode) {
      const track = await this.tracksService
        .getTrack(this.id).toPromise();

      trackId = track?.id;
      trackName = track?.trackName;
      trackImageUrl = track?.trackImageUrl;
      trackUrl = track?.trackUrl;
      trackLength = track?.trackLength;

      artist = track?.artist;
    }

    this.trackForm = new FormGroup({
      id: new FormControl(trackId),
      trackName: new FormControl(trackName),
      trackImageUrl: new FormControl(trackImageUrl),
      trackUrl: new FormControl(trackUrl),
      trackLength: new FormControl(trackLength),

      artist: new FormArray(artist)
    });

  }

  onSubmit() {
    if (this.editMode) {
      this.tracksService.updateTrack(this.trackForm.value)
        .pipe(tap(track => {
          this.track = track;
        })).subscribe();
    } else {
      this.tracksService.addTrack(this.trackForm.value)
        .pipe(tap(newTrack => {
          this.track = newTrack
        })).subscribe();
    }
    this.onCancel();
  }

  onCancel() {
    this.router.navigate(
      ['../'],
      {relativeTo: this.route}
    );
  }
}
