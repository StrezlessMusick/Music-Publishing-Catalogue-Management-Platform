import {Component, OnInit} from '@angular/core';
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
              private router: Router) {
  }

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

  private async initForm() {
    let name = '';
    let image = '';
    let trackCount: number;
    let totalLength: number;

    if (this.editMode) {
      const project = await this.projectsService
        .getProject(this.id).toPromise();

      name = project?.projectName;
      image = project?.projectImageUrl;
      trackCount = project?.numOfTracks;
      totalLength = project?.projectLength;
    }

    this.projectForm = new FormGroup({
      projectName: new FormControl(name),
      projectImageUrl: new FormControl(image),
      numOfTracks: new FormControl(trackCount),
      projectLength: new FormControl(totalLength),
    });

  }

  onSubmit() {

    if (this.editMode) {
      this.projectsService.updateProject(this.projectForm.value)
        .pipe(
          tap(project => this.project = project)
        ).subscribe();
    } else {
      this.projectsService.addProject(this.projectForm.value)
        .pipe(
          tap(newProject => this.project = newProject)
        ).subscribe();
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
