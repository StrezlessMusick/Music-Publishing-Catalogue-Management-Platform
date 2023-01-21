import { Injectable } from '@angular/core';
import {environment} from "../../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Project} from "../interfaces/project";
import {TracksService} from "./tracks.service";

@Injectable({
  providedIn: 'root'
})
export class ProjectsService {

  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient,
              private tracksService: TracksService) {}

  public getProjects(): Observable<Project[]> {
    return this.http
      .get<Project[]>(`${this.apiServerUrl}/v1/projects/all`);
  }

  public getProject(projectId: number): Observable<Project> {
    return this.http
      .get<Project>(`${this.apiServerUrl}/v1/projects/find${projectId}`);
  }

  public addProject(newProject: Project): Observable<Project> {
    return this.http
      .post<Project>(`${this.apiServerUrl}/v1/projects/add`, newProject);
  }

  public updateProject(project: Project): Observable<Project> {
    return this.http
      .put<Project>(`${this.apiServerUrl}/v1/projects/update`, project);
  }

  public removeProject(projectId: number): Observable<void> {
    return this.http
      .delete<void>(`${this.apiServerUrl}/v1/projects/delete${projectId}`);
  }
}
