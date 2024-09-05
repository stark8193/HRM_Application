import { IEmployee } from 'app/shared/model/employee.model';

export interface IJob {
  id?: number;
  jobName?: string;
  description?: string | null;
  employee?: IEmployee | null;
}

export const defaultValue: Readonly<IJob> = {};
