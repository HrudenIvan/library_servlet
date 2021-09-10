package controller.command;

public enum CommandEnum {
	LOGIN {
		{
			command = new LoginCommand();
		}
	},
	LOGOUT {
		{
			command = new LogoutCommand();
		}
	},
	GETALLBOOKS {
		{
			command = new GetAllBooksCommand();
		}
	},
	GETALLUSERS {
		{
			command = new GetAllUsersCommand();
		}
	},
	UPDATEUSER {
		{
			command = new UpdateUserCommand();
		}
	},
	PREPAREUSER {
		{
			command = new PrepareUserCommand();
		}
	},
	PREPAREBOOK {
		{
			command = new PrepareBookCommand();
		}
	},
	UPDATEBOOK {
		{
			command = new UpdateBookCommand();
		}
	},
	ADDUSER {
		{
			command = new AddUserCommand();
		}
	},
	REGISTER {
		{
			command = new RegisterCommand();
		}
	},
	GETALLAUTHORS {
		{
			command = new GetAllAuthorsCommand();
		}
	},
	PREPAREAUTHOR {
		{
			command = new PrepareAuthorsCommand();
		}
	},
	UPDATEAUTHOR {
		{
			command = new UpdateAuthorCommand();
		}
	},
	ADDAUTHORLINK {
		{
			command = new AddAuthorLinkCommand();
		}
	},
	ADDAUTHOR {
		{
			command = new AddAuthorCommand();
		}
	},
	ADDBOOKLINK {
		{
			command = new AddBookLinkCommand();
		}
	},
	ADDBOOK {
		{
			command = new AddBookCommand();
		}
	},
	ADDPUBLISHERLINK {
		{
			command = new AddPublisherLinkCommand();
		}
	},
	ADDPUBLISHER {
		{
			command = new AddPublisherCommand();
		}
	},
	PREPAREPUBLISHER {
		{
			command = new PreparePublisherCommand();
		}
	},
	UPDATEPUBLISHER {
		{
			command = new UpdatePublisherCommand();
		}
	},
	GETALLPUBLISHERS {
		{
			command = new GetAllPublishersCommand();
		}
	},
	DEFAULT {
		{
			command = new DefaultCommand();
		}
	},
	PREPAREBOOKORDER {
		{
			command = new PrepareBookOrderCommand();
		}
	},
	ADDBOOKORDER {
		{
			command = new AddBookOrderCommand();
		}
	},
	PREPARECABINET {
		{
			command = new PrepareCabinetCommand();
		}
	},
	PREPARELIBRARIAN {
		{
			command = new PrepareLibrarianCommand();
		}
	},
	PREPAREBOOKORDERUPDATE {
		{
			command = new PrepareBookOrderUpdateCommand();
		}
	},
	UPDATEBOOKORDER {
		{
			command = new UpdateBookOrderCommand();
		}
	},
	SUBSCRIPTION {
		{
			command = new SubscriptionCommand();
		}
	},
	FINDBOOKSBYTITLE {
		{
			command = new FindBooksByTitleCommand();
		}
	},
	CHANGELOCALE {
		{
			command = new ChangeLocaleCommand();
		}
	};

	Command command;

	Command getCommand() {
		return command;
	}
}