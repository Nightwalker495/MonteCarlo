#include "Application.h"

using namespace app;

int main(int argc, char* argv[])
{
	auto app = new Application(argc, argv);
	auto ret = app->run();
	delete app;

	return ret;
}