#pragma once

namespace app
{
	class Application
	{
	public:
		Application(int argc, char** argv);

		int run() const;
	private:
		bool processCmdArgs(char*& inFileName, unsigned& threshold) const;

	private:
		char** argv_;
		int argc_;
	};
}