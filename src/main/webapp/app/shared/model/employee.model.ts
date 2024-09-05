import dayjs from 'dayjs';
import { IUser } from 'app/shared/model/user.model';
import { IJob } from 'app/shared/model/job.model';
import { ITimeKeeping } from 'app/shared/model/time-keeping.model';
import { ILeaveRequest } from 'app/shared/model/leave-request.model';
import { IDepartment } from 'app/shared/model/department.model';
import { Gender } from 'app/shared/model/enumerations/gender.model';
import { EmpStatus } from 'app/shared/model/enumerations/emp-status.model';

export interface IEmployee {
  id?: number;
  employeeName?: string;
  birthDate?: string;
  gender?: Gender;
  hireDate?: string;
  email?: string | null;
  phone?: number;
  employeeStatus?: EmpStatus;
  taxCode?: number;
  cccd?: number;
  address?: string | null;
  bankAccountNumber?: number | null;
  bank?: string | null;
  bankBranch?: string | null;
  photoPathContentType?: string | null;
  photoPath?: string | null;
  user?: IUser | null;
  job?: IJob | null;
  timeKeepings?: ITimeKeeping[] | null;
  leaveRequests?: ILeaveRequest[] | null;
  department?: IDepartment | null;
}

export const defaultValue: Readonly<IEmployee> = {};
