import { Component, OnInit } from '@angular/core';
import {ProjectsService} from "../../zshared/services/projects.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Project} from "../../zshared/interfaces/project";
import {FormControl, FormGroup} from "@angular/forms";
import {tap} from "rxjs";

@Component({
  selector: 'app-project-edit',
  templateUrl: './project-edit.component.html',
  styleUrls: ['./project-edit.component.css']
})
export class ProjectEditComponent implements OnInit {
  project: Project;
  projectForm: FormGroup;
  id: number;
  editMode = false;

  constructor(private projectsService: ProjectsService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit(): void {
    this.route.params
      .subscribe(
        params => {
          this.id = +params['id'];
          this.editMode = params['id'] != null;
          this.initForm();
        }
      );
  }

  private initForm() {
    let name = '';
    let image = '';
    let trackCount = 0;
    let totalLength = 0;

    if (this.editMode) {
      this.projectsService.getProject(this.id)
        .pipe(
          tap(project => this.project = project)
        ).toPromise()

      name = this.project?.projectName;
      image = this.project?.projectImageUrl;
      trackCount = this.project?.numOfTracks;
      totalLength = this.project?.projectLength;
    }

    this.projectForm = new FormGroup({
      projectName: new FormControl(name),
      projectImageUrl: new FormControl(image),
      numOfTracks: new FormControl(trackCount),
      projectLength: new FormControl(totalLength),
    });

  }

  onSubmit() {

  }

  onCancel() {
    this.router.navigate(
      ['../'],
      {relativeTo: this.route}
    );
  }

}
